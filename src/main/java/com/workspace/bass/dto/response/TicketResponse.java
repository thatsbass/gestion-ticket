package com.workspace.bass.dto.response;
import com.workspace.bass.enums.TicketStatus;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketResponse {
    @NotEmpty(message = "The  field is not empty!")
    private String titre;
    @NotNull(message = "The number phone is not null!")
    private String description;
    @NotNull(message = "The statut field is not null!")
    private TicketStatus statut;
    @NotEmpty(message = "The  field is not empty!")
    private String dateCreation;
}
