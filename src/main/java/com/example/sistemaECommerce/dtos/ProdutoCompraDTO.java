package com.example.sistemaECommerce.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProdutoCompraDTO {

    @NotBlank(message = "O nome do produto é obrigatório.")
    private String nome;
}
