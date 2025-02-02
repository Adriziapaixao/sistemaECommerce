package com.example.sistemaECommerce.validations;

import com.example.sistemaECommerce.dtos.ClienteDTO;
import com.example.sistemaECommerce.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class ClienteValidation {

    private static final String CPF_REGEX = "^[0-9]{11}$"; // Regex para validar CPF (apenas números)
    private static final String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$"; // Regex para validar email

    private ClienteRepository clienteRepository;

    @Autowired
    public void ClienteValidations(ClienteRepository clienteRepository) {
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
        if (!isCpfValido(cpf)) {
            throw new IllegalArgumentException("O CPF informado é inválido.");
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

    /**
     * Valida o CPF utilizando o cálculo do dígito verificador.
     *
     * @param cpf CPF a ser validado.
     * @return true se o CPF for válido, false caso contrário.
     */
    private boolean isCpfValido(String cpf) {
        int[] pesos1 = {10, 9, 8, 7, 6, 5, 4, 3, 2};
        int[] pesos2 = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};

        if (cpf == null || cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        try {
            int digito1 = calcularDigito(cpf.substring(0, 9), pesos1);
            int digito2 = calcularDigito(cpf.substring(0, 9) + digito1, pesos2);
            return cpf.equals(cpf.substring(0, 9) + digito1 + digito2);
        } catch (Exception e) {
            return false;
        }
    }

    private int calcularDigito(String str, int[] pesos) {
        int soma = 0;
        for (int i = 0; i < str.length(); i++) {
            soma += Character.getNumericValue(str.charAt(i)) * pesos[i];
        }
        int resto = soma % 11;
        return resto < 2 ? 0 : 11 - resto;
    }
}
