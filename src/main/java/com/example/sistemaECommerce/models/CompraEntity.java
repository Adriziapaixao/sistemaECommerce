package com.example.sistemaECommerce.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
@Data
@NoArgsConstructor
@Entity
public class CompraEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private ClienteEntity cliente;

    @ManyToMany
    private List<ProdutoEntity> produtos;

}
