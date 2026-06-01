package com.sip.tp.repository;

import com.sip.tp.entity.AnonymousMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AnonymousMessageRepository extends JpaRepository<AnonymousMessage, UUID> {
    List<AnonymousMessage> findAllByThreadIdOrderByCreatedAtAsc(UUID threadId);
}
