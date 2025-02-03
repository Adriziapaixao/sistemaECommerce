package com.example.sistemaECommerce.validations;

import com.example.sistemaECommerce.dtos.ProdutoDTO;
import com.example.sistemaECommerce.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProdutoValidation {
    private final ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoValidation(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public void validarCadastroProduto(ProdutoDTO produtoDTO) {
        validarNomeUnico(produtoDTO.getNome());
        validarPreco(produtoDTO.getPreco());
        validarQuantidade(produtoDTO.getQuantidade());
    }

    private void validarNomeUnico(String nome) {
        if (produtoRepository.findByNome(nome).isPresent()) {
            throw new IllegalArgumentException("Já existe um produto cadastrado com o nome informado.");
        }
    }

    private void validarPreco(Double preco) {
        if (preco == null || preco <= 0) {
            throw new IllegalArgumentException("O preço deve ser maior que 0.");
        }
    }

    private void validarQuantidade(Integer quantidade) {
        if (quantidade == null || quantidade < 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior ou igual a 0.");
        }
    }
}
