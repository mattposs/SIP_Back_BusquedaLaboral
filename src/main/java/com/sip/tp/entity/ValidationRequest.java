package com.sip.tp.entity;

import com.sip.tp.types.converters.RelationTypeConverter;
import com.sip.tp.types.converters.RequestStatusConverter;
import com.sip.tp.types.definition.RelationType;
import com.sip.tp.types.definition.RequestStatus;
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

import java.time.Instant;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ValidationRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "requester_id", nullable = false)
    private Candidate requester;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "validator_id", nullable = false)
    private Candidate validator;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "skill_id", nullable = false)
    private Skill skill;

    @Convert(converter = RelationTypeConverter.class)
    @Column(nullable = false)
    private RelationType relationType;

    @Column(columnDefinition = "TEXT")
    private String message;

    @Convert(converter = RequestStatusConverter.class)
    @Column(nullable = false)
    private RequestStatus status;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Instant createdAt;
}
