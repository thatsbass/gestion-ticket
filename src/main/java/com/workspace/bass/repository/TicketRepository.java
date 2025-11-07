package com.workspace.bass.repository;

import com.workspace.bass.entities.Ticket;
import com.workspace.bass.enums.TicketStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    
    List<Ticket> findByUtilisateurId(Long utilisateurId);
    
    List<Ticket> findByTechnicienId(Long technicienId);
    
    List<Ticket> findByStatut(TicketStatus statut);
    
    List<Ticket> findByUtilisateurIdAndStatut(Long utilisateurId, TicketStatus statut);
    
    List<Ticket> findByTechnicienIdAndStatut(Long technicienId, TicketStatus statut);
    
    boolean existsByIdAndUtilisateurId(Long ticketId, Long utilisateurId);
    
    boolean existsByIdAndTechnicienId(Long ticketId, Long technicienId);
}
