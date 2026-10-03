package com.fatec.sigvs.controller;

// VendaController.java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fatec.sigvs.model.Venda;
import com.fatec.sigvs.model.VendaDTO;
import com.fatec.sigvs.model.VendaResponseDTO;
import com.fatec.sigvs.service.VendaService;

@RestController
@RequestMapping("/api/vendas")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @PostMapping
    public ResponseEntity<?> criarVenda(@RequestBody VendaDTO vendaDTO) {
        try {
            Venda vendaSalva = vendaService.realizarVenda(vendaDTO);

            // Transforma a entidade em um DTO limpo e livre de loops infinitos
            VendaResponseDTO response = new VendaResponseDTO(vendaSalva);

            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
