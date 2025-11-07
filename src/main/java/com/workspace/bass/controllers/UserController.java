package com.workspace.bass.controllers;

import com.workspace.bass.dto.request.UtlisateurRequest;
import com.workspace.bass.dto.response.UtlisateurResponse;
import com.workspace.bass.services.UtilisateurService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UtilisateurService userService;

    @PostMapping("/create")
    public ResponseEntity<UtlisateurResponse> createEtudiant(@Valid @RequestBody UtlisateurRequest request) {
        return ResponseEntity.ok(userService.createUser(request, false));
    }

    @PostMapping("/create/technicien")
    public ResponseEntity<UtlisateurResponse> createTechnicien(@Valid @RequestBody UtlisateurRequest request) {
        return ResponseEntity.ok(userService.createUser(request, true));
    }

    @GetMapping
    public ResponseEntity<List<UtlisateurResponse>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
}
