package com.example.sistemaECommerce.validations;

import com.example.sistemaECommerce.dtos.ClienteDTO;
import com.example.sistemaECommerce.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.regex.Pattern;

public class ClienteValidation {

    private static final String CPF_REGEX = "^[0-9]{11}$"; // Exemplo de regex para CPF (apenas números)
    private static final String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$"; // Regex para validar email

    private ClienteRepository clienteRepository;

    @Autowired
    public void ClienteValidations(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public ClienteValidation(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    /**
     * Valida os dados do cliente antes de realizar o cadastro.
     *
     * @param clienteDTO Dados do cliente a serem validados.
     */
    public void validarCadastroCliente(ClienteDTO clienteDTO) {
        validarNome(clienteDTO.getName());
        validarCpf(clienteDTO.getCpf());
        validarEmail(clienteDTO.getEmail());
        verificarCpfUnico(clienteDTO.getCpf());
        verificarEmailUnico(clienteDTO.getEmail());
    }

    private void validarNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do cliente não pode ser vazio.");
        }
    }

    private void validarCpf(String cpf) {
        if (cpf == null || !Pattern.matches(CPF_REGEX, cpf)) {
            throw new IllegalArgumentException("O CPF informado é inválido. Deve conter 11 dígitos numéricos.");
        }
    }

    private void validarEmail(String email) {
        if (email == null || !Pattern.matches(EMAIL_REGEX, email)) {
            throw new IllegalArgumentException("O email informado é inválido.");
        }
    }

    private void verificarCpfUnico(String cpf) {
        if (clienteRepository.findByCpf(cpf).isPresent()) {
            throw new IllegalArgumentException("Já existe um cliente cadastrado com o CPF informado.");
        }
    }

    private void verificarEmailUnico(String email) {
        if (clienteRepository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("Já existe um cliente cadastrado com o email informado.");
        }
    }
}
