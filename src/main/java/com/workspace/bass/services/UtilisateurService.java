package com.workspace.bass.services;

import com.workspace.bass.dto.request.UtlisateurRequest;
import com.workspace.bass.dto.response.UtlisateurResponse;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public interface UtilisateurService {
   
    @Transactional
    public UtlisateurResponse createUser(UtlisateurRequest dto, boolean isTechnicien);

    public List<UtlisateurResponse> getAllUsers();
    
    public List<UtlisateurResponse> getTechniciens();
}
