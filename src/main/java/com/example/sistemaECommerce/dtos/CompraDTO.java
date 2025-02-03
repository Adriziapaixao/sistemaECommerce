package com.example.sistemaECommerce.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class CompraDTO {

    @NotBlank(message = "O CPF do cliente é obrigatório.")
    private String cpf;

    @NotEmpty(message = "A lista de produtos não pode estar vazia.")
    private List<ProdutoCompraDTO> produtos;
}
