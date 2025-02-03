package com.example.sistemaECommerce.services;

import com.example.sistemaECommerce.dtos.ProdutoDTO;
import com.example.sistemaECommerce.models.ProdutoEntity;
import com.example.sistemaECommerce.repositories.ProdutoRepository;
import com.example.sistemaECommerce.validations.ProdutoValidation;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private final ProdutoRepository produtoRepository;
    private final ProdutoValidation produtoValidation;


    public ProdutoService(ProdutoRepository produtoRepository, ProdutoValidation produtoValidation) {
        this.produtoRepository = produtoRepository;
        this.produtoValidation = produtoValidation;
    }

    public ProdutoEntity cadastrarProduto(ProdutoDTO produtoDTO) {

        produtoValidation.validarCadastroProduto(produtoDTO);
        ProdutoEntity produtoEntity = new ProdutoEntity();
        produtoEntity.setNome(produtoDTO.getNome());
        produtoEntity.setPreco(produtoDTO.getPreco());
        produtoEntity.setQuantidade(produtoDTO.getQuantidade());
        return produtoRepository.save(produtoEntity);
    }

    public List<ProdutoEntity> listarTodosProdutos() {
        return produtoRepository.findAll();
    }

    public void deletarProduto(Long id) {
        if (!produtoRepository.existsById(id)){
            throw new RuntimeException("Produto não encontrado com o ID: " + id);
        }
        produtoRepository.deleteById(id);
    }


    public Optional<ProdutoEntity> buscarPorNome(String nomeProduto) {
        Optional<ProdutoEntity> nome = produtoRepository.findByNome(nomeProduto);
        return nome;
    }

    public void atualizarProduto(Id id) {
        produtoRepository.existsById(id);
    }
}

