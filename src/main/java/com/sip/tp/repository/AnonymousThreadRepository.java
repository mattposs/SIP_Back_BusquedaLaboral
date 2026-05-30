package com.sip.tp.repository;

import com.sip.tp.entity.AnonymousThread;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AnonymousThreadRepository extends JpaRepository<AnonymousThread, UUID> {
}
