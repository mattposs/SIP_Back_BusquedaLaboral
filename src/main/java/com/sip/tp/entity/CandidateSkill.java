package com.sip.tp.entity;

import com.sip.tp.types.converters.ExperienceRangeConverter;
import com.sip.tp.types.converters.SkillLevelConverter;
import com.sip.tp.types.definition.ExperienceRange;
import com.sip.tp.types.definition.SkillLevel;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CandidateSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "candidate_id", nullable = false)
    private Candidate candidate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "skill_id", nullable = false)
    private Skill skill;

    @Convert(converter = ExperienceRangeConverter.class)
    @Column(nullable = false)
    private ExperienceRange experienceRange;

    @Convert(converter = SkillLevelConverter.class)
    private SkillLevel consolidatedLevel;

    @Column(precision = 4, scale = 2)
    private BigDecimal consolidatedScore;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Instant createdAt;
}
