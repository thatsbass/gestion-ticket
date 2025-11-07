package com.workspace.bass.mapper;

import com.workspace.bass.dto.request.TicketRequest;
import com.workspace.bass.dto.response.TicketResponse;
import com.workspace.bass.entities.Ticket;
import com.workspace.bass.entities.Utilisateur;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TicketMapper {
    
    public TicketResponse toDto(Ticket ticket) {
        return TicketResponse.builder()
                .titre(ticket.getTitre())
                .description(ticket.getDescription())
                .statut(ticket.getStatut())
                .dateCreation(ticket.getDateCreation())
                .build();
    }
    
    public List<TicketResponse> toDtoList(List<Ticket> tickets) {
        return tickets.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
    
    public Ticket toEntity(TicketRequest request) {
        return Ticket.builder()
                .titre(request.getTitre())
                .description(request.getDescription())
                .statut(request.getStatut())
                .dateCreation(java.time.LocalDateTime.now().toString())
                .utilisateur(Utilisateur.builder()
                        .id(request.getUtilisateur_id())
                        .build())
                .build();
    }
}
