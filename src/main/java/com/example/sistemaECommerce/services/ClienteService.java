package com.example.sistemaECommerce.services;

import com.example.sistemaECommerce.dtos.ClienteDTO;
import com.example.sistemaECommerce.models.ClienteEntity;
import com.example.sistemaECommerce.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public ClienteEntity cadastrarCliente(ClienteDTO clienteDTO) {
        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setNome(clienteDTO.getName());
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
