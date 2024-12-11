package com.kjawank.gift_exchange_api.service;

import com.kjawank.gift_exchange_api.model.Participant;
import com.kjawank.gift_exchange_api.model.Participant;
import com.kjawank.gift_exchange_api.repository.ParticipantRepository;
import com.kjawank.gift_exchange_api.repository.ParticipantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticipantService {

    private final ParticipantRepository participantRepository;

    public ParticipantService(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    public Participant registrarParticipant(Participant participant) {
        return participantRepository.save(participant);
    }

    public List<Participant> listarParticipants() {
        return participantRepository.findAll();
    }
}
