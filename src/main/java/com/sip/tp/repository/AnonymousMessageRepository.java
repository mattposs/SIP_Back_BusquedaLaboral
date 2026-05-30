package com.sip.tp.repository;

import com.sip.tp.entity.AnonymousMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AnonymousMessageRepository extends JpaRepository<AnonymousMessage, UUID> {
}
