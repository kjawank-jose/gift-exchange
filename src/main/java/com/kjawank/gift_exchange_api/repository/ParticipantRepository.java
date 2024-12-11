package com.kjawank.gift_exchange_api.repository;

import com.kjawank.gift_exchange_api.model.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {
    Optional<Participant> findByEmail(String email);
}
