package com.sip.tp.entity;

import com.sip.tp.types.converters.MatchStatusConverter;
import com.sip.tp.types.definition.MatchStatus;
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
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "candidate_id", nullable = false)
    private Candidate candidate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "offer_id", nullable = false)
    private JobOffer offer;

    @Column(nullable = false)
    private Integer matchScore;

    @Convert(converter = MatchStatusConverter.class)
    @Column(nullable = false)
    private MatchStatus status;

    @Column(nullable = false)
    private Boolean profileRevealed = false;

    private Instant revealedAt;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Instant createdAt;
}