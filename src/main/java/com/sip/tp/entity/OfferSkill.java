package com.sip.tp.entity;

import com.sip.tp.types.converters.RequirementConverter;
import com.sip.tp.types.definition.Requirement;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OfferSkill {

    @EmbeddedId
    private OfferSkillId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("offerId")
    @JoinColumn(name = "offer_id", nullable = false)
    private JobOffer offer;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("skillId")
    @JoinColumn(name = "skill_id", nullable = false)
    private Skill skill;

    @Convert(converter = RequirementConverter.class)
    @Column(nullable = false)
    private Requirement requirement;
}
