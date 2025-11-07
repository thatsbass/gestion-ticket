package com.workspace.bass.controllers;

import com.workspace.bass.dto.request.TicketRequest;
import com.workspace.bass.dto.response.TicketResponse;
import com.workspace.bass.services.TicketService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contrôleur REST pour la gestion des tickets.
 * Fournit des endpoints pour créer, lister, assigner et clôturer des tickets.
 */
@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
public class TicketController {
    
    private final TicketService ticketService;
    
    /**
     * Crée un nouveau ticket.
     *
     * @param request Les détails du ticket à créer
     * @return Le ticket créé avec son statut initial
     */
    @PostMapping
    public ResponseEntity<TicketResponse> createTicket(
            @Valid @RequestBody TicketRequest request) {
        TicketResponse response = ticketService.createTicket(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    
    /**
     * Assigne un technicien à un ticket existant.
     * Change le statut du ticket à 'EN_COURS'.
     *
     * @param ticketId L'identifiant du ticket
     * @param technicienId L'identifiant du technicien à assigner
     * @return Le ticket mis à jour avec le technicien assigné
     */
    @PostMapping("/{ticketId}/assign/{technicienId}")
    public ResponseEntity<TicketResponse> assignTechnician(
            @PathVariable Long ticketId,
            @PathVariable Long technicienId) {
        TicketResponse response = ticketService.assignerTechnicien(ticketId, technicienId);
        return ResponseEntity.ok(response);
    }
    
    /**
     * Clôture un ticket existant.
     * Change le statut du ticket à 'RESOLU'.
     *
     * @param ticketId L'identifiant du ticket à clôturer
     * @return Le ticket mis à jour avec le statut 'RESOLU'
     */
    @PostMapping("/{ticketId}/close")
    public ResponseEntity<TicketResponse> closeTicket(
            @PathVariable Long ticketId) {
        TicketResponse response = ticketService.cloturerTicket(ticketId);
        return ResponseEntity.ok(response);
    }
    
    /**
     * Liste Ticket
     * @return tous les tickets
     */
    @GetMapping
    public ResponseEntity<List<TicketResponse>> getAllTickets() {
        return ResponseEntity.ok(ticketService.getAllTickets());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<TicketResponse> getTicketById(
            @PathVariable Long id) {
        return ResponseEntity.ok(ticketService.getTicketById(id));
    }
}