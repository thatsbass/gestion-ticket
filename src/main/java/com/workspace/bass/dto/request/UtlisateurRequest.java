package com.workspace.bass.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UtlisateurRequest {
    @NotEmpty(message = "The name field is not empty!")
    private String nom;
    @NotNull(message = "The number phone is not null!")
    private String email;
    // Le role s'est automatiquement ajouté on l'ignore
}
