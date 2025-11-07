package com.workspace.bass.dto.request;

import com.workspace.bass.enums.TicketStatus;

import jakarta.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketRequest {
    @NotEmpty(message = "The  field is not empty!")
    private String titre;
    @NotNull(message = "The number phone is not null!")
    private String description;
    // Le statut est défini automatiquement côté serveur
    @JsonIgnore
    private TicketStatus statut;
    private Long utilisateur_id;
}
