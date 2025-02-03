package com.example.sistemaECommerce.controllers;

import com.example.sistemaECommerce.dtos.ProdutoDTO;
import com.example.sistemaECommerce.models.ProdutoEntity;
import com.example.sistemaECommerce.services.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
@Validated
public class ProdutoController {

    @Autowired
    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public ResponseEntity<List<ProdutoEntity>> listarProdutos() {
        List<ProdutoEntity> produtos = produtoService.listarTodosProdutos();
        return ResponseEntity.ok(produtos);
    }

    // Endpoint para cadastrar um novo produto
    @PostMapping
    public ResponseEntity<ProdutoEntity> cadastrarProduto(@Valid @RequestBody ProdutoDTO produtoDTO) {
        ProdutoEntity produtoCadastrado = produtoService.cadastrarProduto(produtoDTO);
        return ResponseEntity.ok(produtoCadastrado);
    }

    // Endpoint para deletar um produto pelo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {
        produtoService.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }
}
