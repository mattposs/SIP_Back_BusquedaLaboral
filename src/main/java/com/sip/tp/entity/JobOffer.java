package com.sip.tp.entity;

import com.sip.tp.types.converters.ModalityConverter;
import com.sip.tp.types.converters.OfferStatusConverter;
import com.sip.tp.types.converters.SeniorityConverter;
import com.sip.tp.types.definition.Modality;
import com.sip.tp.types.definition.OfferStatus;
import com.sip.tp.types.definition.Seniority;
import jakarta.persistence.*;
import lombok.*;
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
public class JobOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recruiter_id", nullable = false)
    private Recruiter recruiter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @Column(nullable = false)
    private String title;

    @Convert(converter = ModalityConverter.class)
    @Column(nullable = false)
    private Modality modality;

    @Convert(converter = SeniorityConverter.class)
    @Column(nullable = false)
    private Seniority seniority;

    @Column(columnDefinition = "TEXT")
    private String description;

    private BigDecimal salaryMin;
    private BigDecimal salaryMax;

    @Column(columnDefinition = "TEXT")
    private String benefits;

    private String location;

    @Convert(converter = OfferStatusConverter.class)
    @Column(nullable = false)
    private OfferStatus status;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Instant createdAt;
}
