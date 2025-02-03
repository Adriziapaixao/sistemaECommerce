package com.example.sistemaECommerce.controllers;

import com.example.sistemaECommerce.dtos.CompraDTO;
import com.example.sistemaECommerce.models.CompraEntity;
import com.example.sistemaECommerce.services.CompraService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/compras")
public class CompraController {

    private final CompraService compraService;

    @Autowired
    public CompraController(CompraService compraService) {
        this.compraService = compraService;
    }

    @PostMapping
    public ResponseEntity<?> realizarCompra(@Valid @RequestBody CompraDTO compraDTO) {
        try {
            CompraEntity compra = compraService.realizarCompra(compraDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(compra);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"erro\": \"" + e.getMessage() + "\"}");
        }
    }
}