package com.sip.tp.repository;

import com.sip.tp.entity.OfferSkill;
import com.sip.tp.entity.OfferSkillId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OfferSkillRepository extends JpaRepository<OfferSkill, OfferSkillId> {
    List<OfferSkill> findAllByOffer_Id(UUID offerId);

    void deleteAllByOffer_Id(UUID offerId);
}
