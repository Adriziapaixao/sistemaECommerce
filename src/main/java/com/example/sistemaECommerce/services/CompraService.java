package com.example.sistemaECommerce.services;

import com.example.sistemaECommerce.dtos.CompraDTO;
import com.example.sistemaECommerce.models.ClienteEntity;
import com.example.sistemaECommerce.models.CompraEntity;
import com.example.sistemaECommerce.models.ProdutoEntity;
import com.example.sistemaECommerce.repositories.ClienteRepository;
import com.example.sistemaECommerce.repositories.CompraRepository;
import com.example.sistemaECommerce.repositories.ProdutoRepository;
import com.example.sistemaECommerce.validations.CompraValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompraService {

    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;
    private final CompraRepository compraRepository;
    private final CompraValidation compraValidation;

    @Autowired
    public CompraService(ClienteRepository clienteRepository, ProdutoRepository produtoRepository,
                         CompraRepository compraRepository, CompraValidation compraValidation) {
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
        this.compraRepository = compraRepository;
        this.compraValidation = compraValidation;
    }

    public CompraEntity realizarCompra(CompraDTO compraDTO) {
        // Valida a compra
        compraValidation.validarCompra(compraDTO);

        // Identifica o cliente pelo CPF
        ClienteEntity cliente = clienteRepository.findByCpf(compraDTO.getCpf())
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado com o CPF informado."));

        // Busca os produtos e atualiza o estoque
        List<ProdutoEntity> produtos = compraDTO.getProdutos().stream()
                .map(produtoDTO -> {
                    ProdutoEntity produto = produtoRepository.findByNome(produtoDTO.getNome())
                            .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado: " + produtoDTO.getNome()));
                    produto.setQuantidade(produto.getQuantidade() - 1);
                    produtoRepository.save(produto);
                    return produto;
                })
                .collect(Collectors.toList());

        // Registra a compra
        CompraEntity compra = new CompraEntity();
        compra.setCliente(cliente);
        compra.setProdutos(produtos);
        return compraRepository.save(compra);
    }

}
