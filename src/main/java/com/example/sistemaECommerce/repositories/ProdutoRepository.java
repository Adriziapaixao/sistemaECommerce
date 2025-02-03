package com.example.sistemaECommerce.repositories;

import com.example.sistemaECommerce.models.ProdutoEntity;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {
    Optional<ProdutoEntity> findByNome(String nome);

    void existsById(Id id);
}
