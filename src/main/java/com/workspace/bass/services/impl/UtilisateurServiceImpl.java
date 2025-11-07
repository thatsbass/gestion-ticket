package com.workspace.bass.services.impl;

import com.workspace.bass.dto.request.UtlisateurRequest;
import com.workspace.bass.dto.response.UtlisateurResponse;
import com.workspace.bass.entities.Utilisateur;
import com.workspace.bass.enums.Role;
import com.workspace.bass.mapper.UtlisateurMapper;
import com.workspace.bass.repository.UtilisateurRepository;
import com.workspace.bass.services.UtilisateurService;
import com.workspace.bass.validators.ObjectsValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UtilisateurServiceImpl implements UtilisateurService {
    private final UtilisateurRepository utilisateurRepository;
    private final ObjectsValidator<UtlisateurRequest> validator;
    private final UtlisateurMapper userMapper;

    @Override
    @Transactional
    public UtlisateurResponse createUser(UtlisateurRequest dto, boolean isTechnicien) {
        validator.validate(dto);
     
        if (utilisateurRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalStateException("L'email est déjà utilisé");
        }

        Utilisateur utilisateur = userMapper.toEntity(dto);
        utilisateur.setRole(isTechnicien ? Role.TECHNICIEN : Role.ETUDIANT);
        
        return userMapper.toDto(utilisateurRepository.save(utilisateur));
    }

    @Override
    public List<UtlisateurResponse> getAllUsers() {
        return userMapper.toDtoList(utilisateurRepository.findAll());
    }
    
    @Override
    public List<UtlisateurResponse> getTechniciens() {
        return userMapper.toDtoList(utilisateurRepository.findByRole(Role.TECHNICIEN));
    }
}
