package com.workspace.bass.dto.response;

import com.workspace.bass.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UtlisateurResponse {
    private String nom;
    private String email;
    private Role role;
}
