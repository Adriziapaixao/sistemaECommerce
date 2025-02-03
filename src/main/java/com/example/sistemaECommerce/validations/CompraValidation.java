package com.example.sistemaECommerce.validations;

import com.example.sistemaECommerce.dtos.CompraDTO;
import com.example.sistemaECommerce.dtos.ProdutoCompraDTO;
import com.example.sistemaECommerce.models.ProdutoEntity;
import com.example.sistemaECommerce.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CompraValidation {

    private final ProdutoRepository produtoRepository;

    @Autowired
    public CompraValidation(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public void validarCompra(CompraDTO compraDTO) {
        List<String> produtosEmFalta = new ArrayList<>();

        for (ProdutoCompraDTO produtoDTO : compraDTO.getProdutos()) {
            Optional<ProdutoEntity> produto = produtoRepository.findByNome(produtoDTO.getNome());
            if (produto.isEmpty() || produto.get().getQuantidade() <= 0) {
                produtosEmFalta.add(produtoDTO.getNome());
            }
        }

        if (!produtosEmFalta.isEmpty()) {
            throw new IllegalArgumentException("Produto em falta: " + String.join(", ", produtosEmFalta));
        }
    }
}
