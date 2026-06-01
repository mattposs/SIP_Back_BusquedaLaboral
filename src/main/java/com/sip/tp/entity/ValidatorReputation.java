package com.sip.tp.entity;

import com.sip.tp.types.converters.ReputationLevelConverter;
import com.sip.tp.types.definition.ReputationLevel;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
