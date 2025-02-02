package com.example.sistemaECommerce.controllers;

import com.example.sistemaECommerce.dtos.ClienteDTO;
import com.example.sistemaECommerce.models.ClienteEntity;
import com.example.sistemaECommerce.repositories.ClienteRepository;
import com.example.sistemaECommerce.services.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/clientes")
@Validated
public class ClienteController {

    @Autowired
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<?> cadastrarCliente(@Valid @RequestBody ClienteDTO clienteDTO) {
        try {
            ClienteEntity clienteCadastrado = clienteService.cadastrarCliente(clienteDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(clienteCadastrado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<ClienteEntity> getClienteByCpf(@PathVariable String cpf) {
        ClienteEntity cliente = clienteService.getClienteByCpf(cpf);
        return ResponseEntity.ok(cliente);
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<ClienteEntity> atualizarCliente(
         @PathVariable String cpf,
         @Valid @RequestBody ClienteDTO clienteDTO) {
        ClienteEntity clienteAtualizado = clienteService.atualizaDadosCliente(cpf, clienteDTO.toEntity());
        return ResponseEntity.ok(clienteAtualizado);
    }

    @GetMapping
    public ResponseEntity<List<ClienteEntity>> listarClientes() {
        List<ClienteEntity> clientes = clienteService.listarTodosClientes();
        return ResponseEntity.ok(clientes);
    }


}


