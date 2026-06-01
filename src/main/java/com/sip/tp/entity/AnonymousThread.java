package com.sip.tp.entity;

import com.sip.tp.types.converters.ThreadCategoryConverter;
import com.sip.tp.types.converters.ThreadStatusConverter;
import com.sip.tp.types.definition.ThreadCategory;
import com.sip.tp.types.definition.ThreadStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnonymousThread {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String anonymousCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "candidate_id", nullable = false)
    private Candidate candidate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "offer_id", nullable = false)
    private JobOffer offer;

    @Convert(converter = ThreadCategoryConverter.class)
    @Column(nullable = false)
    private ThreadCategory category;

    @Convert(converter = ThreadStatusConverter.class)
    @Column(nullable = false)
    private ThreadStatus status;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Instant createdAt;
}