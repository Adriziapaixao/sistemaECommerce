package com.example.sistemaECommerce.dtos;

import com.example.sistemaECommerce.models.ClienteEntity;
import com.example.sistemaECommerce.models.ProdutoEntity;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProdutoDTO {
    @NotBlank(message = "O nome do produto é obrigatório.")
    private String nome;

    @NotNull(message = "O preço do produto é obrigatório.")
    @DecimalMin(value = "0.01", message = "O preço deve ser maior que 0.")
    private Double preco;

    @NotNull(message = "A quantidade do produto é obrigatória.")
    @Min(value = 0, message = "A quantidade deve ser maior ou igual a 0.")
    private Integer quantidade;

    public ProdutoEntity toEntity() {
        ProdutoEntity produtoEntity = new ProdutoEntity();
        produtoEntity.setNome(this.nome);
        produtoEntity.setPreco(this.preco);
        produtoEntity.setQuantidade(this.quantidade);
        return produtoEntity;
    }
}
