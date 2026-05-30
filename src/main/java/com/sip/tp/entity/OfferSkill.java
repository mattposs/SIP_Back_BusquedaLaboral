package com.sip.tp.entity;

import com.sip.tp.types.converters.RequirementConverter;
import com.sip.tp.types.definition.Requirement;
import jakarta.persistence.*;
import lombok.*;

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
