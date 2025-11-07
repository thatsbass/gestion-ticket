package com.workspace.bass.mapper;


import com.workspace.bass.dto.request.UtlisateurRequest;
import com.workspace.bass.dto.response.UtlisateurResponse;
import com.workspace.bass.entities.Utilisateur;
import com.workspace.bass.enums.Role;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UtlisateurMapper {

    public UtlisateurResponse toDto(Utilisateur user){
        return UtlisateurResponse.builder()
                .nom(user.getNom())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    public List<UtlisateurResponse> toDtoList(List<Utilisateur> userList){
        return userList.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }


    public Utilisateur toEntity(UtlisateurRequest req){
        return Utilisateur.builder()
                .nom(req.getNom())
                .email(req.getEmail())
                .role(Role.ETUDIANT)
                .build();
    }
}
