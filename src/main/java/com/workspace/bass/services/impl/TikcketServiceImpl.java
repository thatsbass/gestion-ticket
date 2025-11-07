package com.workspace.bass.services.impl;

import com.workspace.bass.dto.request.TicketRequest;
import com.workspace.bass.dto.response.TicketResponse;
import com.workspace.bass.entities.Ticket;
import com.workspace.bass.entities.Utilisateur;
import com.workspace.bass.enums.TicketStatus;
import com.workspace.bass.exceptions.ResourceException;
import com.workspace.bass.exceptions.ResourceNotFoundException;
import com.workspace.bass.mapper.TicketMapper;
import com.workspace.bass.repository.TicketRepository;
import com.workspace.bass.repository.UtilisateurRepository;
import com.workspace.bass.services.TicketService;
import com.workspace.bass.validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TikcketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final TicketMapper ticketMapper;
    private final ObjectsValidator<TicketRequest> validator;

    @Override
    @Transactional
    public TicketResponse createTicket(TicketRequest dto) {
        validator.validate(dto);
        
        Long utilisateurId = dto.getUtilisateur_id();
        if (utilisateurId == null) {
            throw new ResourceException("L'ID de l'utilisateur est requis");
        }
        Utilisateur createur = utilisateurRepository.findById(utilisateurId)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur non trouvé avec l'ID: " + utilisateurId));
        
        if (createur.getRole().name().equals("TECHNICIEN")) {
            throw new ResourceException("Un technicien ne peut pas créer de ticket");
        }
        
        Ticket ticket = ticketMapper.toEntity(dto);
        ticket.setStatut(TicketStatus.OUVERT);
        ticket.setDateCreation(LocalDateTime.now().toString());
        
        return ticketMapper.toDto(ticketRepository.save(ticket));
    }

    @Override
    @Transactional
    public TicketResponse assignerTechnicien(Long ticketId, Long technicienId) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket non trouvé avec l'ID: " + ticketId));
                
        Utilisateur technicien = utilisateurRepository.findById(technicienId)
                .orElseThrow(() -> new ResourceNotFoundException("Technicien non trouvé avec l'ID: " + technicienId));
        
        ticket.setStatut(TicketStatus.EN_COURS);
        ticket.setTechnicien(technicien);
        
        return ticketMapper.toDto(ticketRepository.save(ticket));
    }

    @Override
    @Transactional
    public TicketResponse cloturerTicket(Long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket non trouvé avec l'ID: " + ticketId));
        
        if (ticket.getStatut() == TicketStatus.RESOLU) {
            throw new ResourceException("Le ticket est déjà résolu");
        }
        
        if (ticket.getTechnicien() == null) {
            throw new ResourceException("Impossible de clôturer un ticket sans technicien assigné");
        }
        
        ticket.setStatut(TicketStatus.RESOLU);
        return ticketMapper.toDto(ticketRepository.save(ticket));
    }

    @Override
    public List<TicketResponse> getAllTickets() {
        return ticketMapper.toDtoList(ticketRepository.findAll());
    }

    @Override
    public TicketResponse getTicketById(Long id) {
        if (id == null) {
            throw new ResourceException("L'ID du ticket est requis");
        }
        return ticketRepository.findById(id)
                .map(ticketMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket non trouvé avec l'ID: " + id));
    }
}
