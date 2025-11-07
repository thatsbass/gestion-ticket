package com.workspace.bass.services;

import com.workspace.bass.dto.request.TicketRequest;
import com.workspace.bass.dto.response.TicketResponse;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


public interface TicketService {
    
    @Transactional
    public TicketResponse createTicket(TicketRequest dto);

    @Transactional
    public TicketResponse assignerTechnicien(Long ticketId, Long technicienId);

    @Transactional
    public TicketResponse cloturerTicket(Long ticketId);

    public List<TicketResponse> getAllTickets();

    public TicketResponse getTicketById(Long id);   
    
}
