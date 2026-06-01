package com.sip.tp.entity;

import com.sip.tp.types.converters.ReputationLevelConverter;
import com.sip.tp.types.definition.ReputationLevel;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ValidatorReputation {

    @Id
    private UUID userId;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Candidate candidate;

    @Convert(converter = ReputationLevelConverter.class)
    @Column(nullable = false)
    private ReputationLevel reputationLevel;

    @Column(precision = 4, scale = 2, nullable = false)
    private BigDecimal reputationScore;

    @Column(nullable = false)
    private Integer platformYears;

    @Column(nullable = false)
    private Integer totalValidations;

    @Column(nullable = false)
    private Integer successRate;

    @Column(nullable = false)
    private String seniority;

    @Column(nullable = false)
    private Boolean identityVerified;
}
