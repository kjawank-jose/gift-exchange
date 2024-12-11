package com.kjawank.gift_exchange_api.repository;

import com.kjawank.gift_exchange_api.model.GiftAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GiftAssignmentRepository extends JpaRepository<GiftAssignment, Long> {
}
