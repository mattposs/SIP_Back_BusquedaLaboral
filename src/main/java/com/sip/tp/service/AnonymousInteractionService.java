package com.sip.tp.service;

import com.sip.tp.dto.message.AnonymousThreadDetailResponse;
import com.sip.tp.dto.message.AnonymousThreadResponse;
import com.sip.tp.entity.*;
import com.sip.tp.repository.AnonymousMessageRepository;
import com.sip.tp.repository.AnonymousThreadRepository;
import com.sip.tp.repository.CandidateRepository;
import com.sip.tp.repository.JobOfferRepository;
import com.sip.tp.types.definition.MatchStatus;
import com.sip.tp.types.definition.SenderType;
import com.sip.tp.types.definition.ThreadCategory;
import com.sip.tp.types.definition.ThreadStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnonymousInteractionService {

    private final AnonymousThreadRepository threadRepository;
    private final AnonymousMessageRepository messageRepository;
    private final CandidateRepository candidateRepository;
    private final JobOfferRepository offerRepository;

    @Transactional
    public UUID createThread(UUID candidateId, UUID offerId, String categoryCode, String initialMessage) {
        Candidate candidate = candidateRepository.getReferenceById(candidateId);
        JobOffer offer = offerRepository.getReferenceById(offerId);

        ThreadCategory category = switch (categoryCode.toUpperCase()) {
            case "SALARY" -> new ThreadCategory.Salary();
            case "CULTURE" -> new ThreadCategory.Culture();
            case "STACK" -> new ThreadCategory.Stack();
            case "BENEFITS" -> new ThreadCategory.Benefits();
            case "MODALITY" -> new ThreadCategory.Modality();
            default -> new ThreadCategory.Other();
        };

        String code = "#A-" + UUID.randomUUID().toString().substring(0, 4).toUpperCase();

        AnonymousThread thread = AnonymousThread.builder()
                .candidate(candidate).offer(offer).category(category)
                .status(new ThreadStatus.Pending()).anonymousCode(code).build();

        AnonymousThread saved = threadRepository.save(thread);

        sendMessageInternal(saved, new SenderType.Candidate(), initialMessage);
        return saved.getId();
    }

    @Transactional(readOnly = true)
    public List<AnonymousThreadResponse> getCandidateThreads(UUID candidateId) {
        return threadRepository.findAllByCandidateId(candidateId).stream()
                .map(t -> new AnonymousThreadResponse(t.getId(), t.getAnonymousCode(), t.getCategory().code(), t.getStatus().code(), t.getCreatedAt()))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public AnonymousThreadDetailResponse getThreadDetail(UUID threadId, UUID requesterId) {
        AnonymousThread thread = threadRepository.findById(threadId).orElseThrow();
        boolean isCandidate = thread.getCandidate().getId().equals(requesterId);
        boolean isRecruiter = thread.getOffer().getRecruiter().getId().equals(requesterId);
        if (!isCandidate && !isRecruiter) throw new SecurityException("Unauthorized");

        List<AnonymousThreadDetailResponse.AnonymousMessageResponse> messages = messageRepository.findAllByThreadIdOrderByCreatedAtAsc(threadId).stream()
                .map(m -> new AnonymousThreadDetailResponse.AnonymousMessageResponse(m.getId(), m.getSenderType().code(), m.getContent(), m.getCreatedAt()))
                .collect(Collectors.toList());
        return new AnonymousThreadDetailResponse(thread.getId(), thread.getAnonymousCode(), thread.getCategory().code(),
                thread.getStatus().code(), messages, thread.getCreatedAt());
    }

    @Transactional(readOnly = true)
    public List<AnonymousThreadResponse> getOfferThreads(UUID recruiterId, UUID offerId) {
        return threadRepository.findAllByOfferId(offerId).stream()
                .filter(t -> t.getOffer().getRecruiter().getId().equals(recruiterId))
                .map(t -> new AnonymousThreadResponse(t.getId(), t.getAnonymousCode(), t.getCategory().code(), t.getStatus().code(), t.getCreatedAt()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void sendMessage(UUID senderId, UUID threadId, String content) {
        AnonymousThread thread = threadRepository.findById(threadId).orElseThrow();
        SenderType senderType;

        // Determine if sender is the candidate of the thread or the recruiter of the offer
        if (thread.getCandidate().getId().equals(senderId)) {
            senderType = new SenderType.Candidate();
        } else if (thread.getOffer().getRecruiter().getId().equals(senderId)) {
            senderType = new SenderType.Recruiter();
            thread.setStatus(new ThreadStatus.Responded());
            threadRepository.save(thread);
        } else {
            throw new SecurityException("Unauthorized to post in this thread");
        }

        sendMessageInternal(thread, senderType, content);
    }

    private void sendMessageInternal(AnonymousThread thread, SenderType senderType, String content) {
        AnonymousMessage msg = AnonymousMessage.builder()
                .thread(thread).senderType(senderType).content(content).build();
        messageRepository.save(msg);
    }

    @Transactional
    public void revealProfile(Match match) {
        if (match.getProfileRevealed()) throw new IllegalStateException("Already revealed.");
        match.setStatus(new MatchStatus.Interested());
        match.setProfileRevealed(true);
        match.setRevealedAt(Instant.now());
    }

    @Transactional
    public void declineInterest(Match match) {
        match.setStatus(new MatchStatus.NotInterested());
    }
}