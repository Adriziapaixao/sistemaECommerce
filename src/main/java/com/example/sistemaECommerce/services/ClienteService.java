package com.example.sistemaECommerce.services;

import com.example.sistemaECommerce.dtos.ClienteDTO;
import com.example.sistemaECommerce.models.ClienteEntity;
import com.example.sistemaECommerce.repositories.ClienteRepository;
import com.example.sistemaECommerce.validations.ClienteValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private final ClienteRepository clienteRepository;

    private final ClienteValidation clienteValidation;

    public ClienteService(ClienteRepository clienteRepository, ClienteValidation clienteValidation) {
        this.clienteRepository = clienteRepository;
        this.clienteValidation = clienteValidation;
    }

    public ClienteEntity cadastrarCliente(ClienteDTO clienteDTO) {
        clienteValidation.validarCadastroCliente(clienteDTO);
        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setNome(clienteDTO.getNome());
        clienteEntity.setCpf(clienteDTO.getCpf());
        clienteEntity.setEmail(clienteDTO.getEmail());
        return clienteRepository.save(clienteEntity);
    }

    public ClienteEntity getClienteByCpf(String cpf) {
        return clienteRepository.findByCpf(cpf)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com o CPF: " + cpf));
    }

    public ClienteEntity atualizaDadosCliente(String cpf, ClienteEntity atualizadoClienteDTO) {
        if (cpf == null) {
            throw new IllegalArgumentException("O CPF do cliente não pode ser nulo.");
        }
        ClienteEntity clienteExistente = clienteRepository.findByCpf(cpf)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com o CPF: " + cpf));

        clienteExistente.setNome(atualizadoClienteDTO.getNome());
        clienteExistente.setCpf(atualizadoClienteDTO.getCpf());
        clienteExistente.setEmail(atualizadoClienteDTO.getEmail());
        return clienteRepository.save(clienteExistente);
    }

    public List<ClienteEntity> listarTodosClientes() {
        return clienteRepository.findAll();
    }
}
