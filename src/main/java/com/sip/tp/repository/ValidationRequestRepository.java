package com.sip.tp.repository;

import com.sip.tp.entity.ValidationRequest;
import com.sip.tp.types.definition.RequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ValidationRequestRepository extends JpaRepository<ValidationRequest, UUID> {
    List<ValidationRequest> findAllByValidatorIdAndStatus(UUID validatorId, RequestStatus status);
}
