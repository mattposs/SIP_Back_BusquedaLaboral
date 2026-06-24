package com.sip.tp.service.domain.messaging;

import com.sip.tp.dto.message.AnonymousThreadDetailResponse;
import com.sip.tp.dto.message.AnonymousThreadResponse;
import com.sip.tp.entity.*;
import com.sip.tp.repository.AnonymousMessageRepository;
import com.sip.tp.repository.AnonymousThreadRepository;
import com.sip.tp.repository.CandidateRepository;
import com.sip.tp.repository.JobOfferRepository;
import com.sip.tp.types.definition.MatchStatus;
import com.sip.tp.types.definition.SenderType;
import com.sip.tp.types.definition.ThreadStatus;
import com.sip.tp.util.converter.RequestConverter;
import com.sip.tp.util.converter.ResponseConverter;
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
    private final RequestConverter requestConverter;
    private final ResponseConverter responseConverter;

    @Transactional
    public UUID createThread(UUID candidateId, UUID offerId, String categoryCode, String initialMessage) {
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new IllegalArgumentException("Candidate not found"));
        JobOffer offer = offerRepository.findById(offerId)
                .orElseThrow(() -> new IllegalArgumentException("Offer not found"));

        String code = "#A-" + UUID.randomUUID().toString().substring(0, 4).toUpperCase();

        AnonymousThread thread = AnonymousThread.builder()
                .candidate(candidate)
                .offer(offer)
                .category(requestConverter.toThreadCategory(categoryCode))
                .status(new ThreadStatus.Pending())
                .anonymousCode(code)
                .build();

        AnonymousThread saved = threadRepository.save(thread);
        sendMessageInternal(saved, new SenderType.Candidate(), initialMessage);
        return saved.getId();
    }

    @Transactional(readOnly = true)
    public List<AnonymousThreadResponse> getCandidateThreads(UUID candidateId) {
        return threadRepository.findAllByCandidateId(candidateId).stream()
                .map(responseConverter::toAnonymousThreadResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public AnonymousThreadDetailResponse getThreadDetail(UUID threadId, UUID requesterId) {
        AnonymousThread thread = threadRepository.findById(threadId).orElseThrow();
        boolean isCandidate = thread.getCandidate().getId().equals(requesterId);
        boolean isRecruiter = thread.getOffer().getRecruiter().getId().equals(requesterId);
        if (!isCandidate && !isRecruiter) throw new SecurityException("Unauthorized");

        List<AnonymousMessage> messages = messageRepository.findAllByThreadIdOrderByCreatedAtAsc(threadId);
        return responseConverter.toAnonymousThreadDetailResponse(thread, messages);
    }

    @Transactional(readOnly = true)
    public List<AnonymousThreadResponse> getRecruiterThreads(UUID recruiterId) {
        return threadRepository.findAllByOfferRecruiterId(recruiterId).stream()
                .map(responseConverter::toAnonymousThreadResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<AnonymousThreadResponse> getOfferThreads(UUID recruiterId, UUID offerId) {
        return threadRepository.findAllByOfferId(offerId).stream()
                .filter(t -> t.getOffer().getRecruiter().getId().equals(recruiterId))
                .map(responseConverter::toAnonymousThreadResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public void sendMessage(UUID senderId, UUID threadId, String content) {
        AnonymousThread thread = threadRepository.findById(threadId).orElseThrow();
        SenderType senderType;

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
                .thread(thread)
                .senderType(senderType)
                .content(content)
                .build();
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
