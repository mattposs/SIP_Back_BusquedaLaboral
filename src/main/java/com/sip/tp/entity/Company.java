package com.sip.tp.entity;

import com.sip.tp.types.converters.CompanySizeConverter;
import com.sip.tp.types.converters.IndustryConverter;
import com.sip.tp.types.definition.CompanySize;
import com.sip.tp.types.definition.Industry;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    private String logo;
    private String website;

    @Convert(converter = IndustryConverter.class)
    private Industry industry;

    @Convert(converter = CompanySizeConverter.class)
    private CompanySize size;

    @Column(columnDefinition = "TEXT")
    private String cultureDescription;

    @Column(nullable = false)
    private Boolean isPartner = false;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Instant createdAt;
}