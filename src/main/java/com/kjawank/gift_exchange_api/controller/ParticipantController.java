package com.kjawank.gift_exchange_api.controller;

import com.kjawank.gift_exchange_api.model.Participant;
import com.kjawank.gift_exchange_api.service.GiftExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/participants")
public class ParticipantController {

    @Autowired
    private GiftExchangeService giftExchangeService;

    // Registrar un participante
    @PostMapping("/api/register")
    public ResponseEntity<Participant> registerParticipant(@RequestParam String name, @RequestParam String email) {
        return ResponseEntity.ok(giftExchangeService.registerParticipant(name, email));
    }

    // Asignar regalos
    @PostMapping("/assign")
    public ResponseEntity<String> assignGifts() {
        giftExchangeService.assignGifts();
        return ResponseEntity.ok("Regalos asignados exitosamente.");
    }

    // Consultar destinatario de un participante
    @GetMapping("/receiver")
    public ResponseEntity<Participant> getReceiver(@RequestParam String email) {
        return ResponseEntity.ok(giftExchangeService.getReceiver(email));
    }

    // Listar todos los participantes (solo admins)
    @GetMapping("/")
    public List<Participant> listParticipants() {
        return giftExchangeService.getAllParticipants();
    }
}
