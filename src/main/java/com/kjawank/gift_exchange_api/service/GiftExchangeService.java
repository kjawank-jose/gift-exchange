package com.kjawank.gift_exchange_api.service;

import com.kjawank.gift_exchange_api.model.GiftAssignment;
import com.kjawank.gift_exchange_api.model.Participant;
import com.kjawank.gift_exchange_api.repository.GiftAssignmentRepository;
import com.kjawank.gift_exchange_api.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class GiftExchangeService {

    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private GiftAssignmentRepository giftAssignmentRepository;

    // Registrar un nuevo participante
    public Participant registerParticipant(String name, String email) {
        Optional<Participant> existingParticipant = participantRepository.findByEmail(email);
        if (existingParticipant.isPresent()) {
            throw new IllegalArgumentException("El participante ya existe con ese correo.");
        }

        Participant participant = new Participant();
        participant.setName(name);
        participant.setEmail(email);
        return participantRepository.save(participant);
    }

    // Asignar regalos aleatorios entre los participantes
    public void assignGifts() {
        List<Participant> participants = participantRepository.findAll();
        if (participants.size() < 2) {
            throw new IllegalStateException("Se requieren al menos 2 participantes.");
        }

        Collections.shuffle(participants);  // Mezcla aleatoriamente los participantes

        for (int i = 0; i < participants.size(); i++) {
            Participant giver = participants.get(i);
            Participant receiver = participants.get((i + 1) % participants.size()); // Evita asignarse a sí mismo
            GiftAssignment assignment = new GiftAssignment();
            assignment.setGiverId(giver.getId());
            assignment.setReceiverId(receiver.getId());
            giftAssignmentRepository.save(assignment);
        }
    }

    // Obtener destinatario de un participante por email
    public Participant getReceiver(String email) {
        Participant giver = participantRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("Participante no encontrado."));
        GiftAssignment assignment = giftAssignmentRepository.findById(giver.getId())
                .orElseThrow(() -> new IllegalStateException("No se ha asignado un regalo."));
        return participantRepository.findById(assignment.getReceiverId()).orElseThrow(() -> new IllegalArgumentException("Destinatario no encontrado."));
    }

    // Obtener todos los participantes
    public List<Participant> getAllParticipants() {
        return participantRepository.findAll();
    }
}
