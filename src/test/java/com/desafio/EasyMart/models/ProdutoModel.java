package com.desafio.EasyMart.models;

import com.desafio.EasyMart.enums.CategoriaProduto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "product")
public class ProdutoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é obrigatorio")
    @Size(min = 2, max = 100, message = "O nome deve ter enre 2 e 100 caracteres.")
    private String name;

    @Size(max = 500, message = "A descrição não pode ultrapassar os 500 carateres.")
    private String descricao;

    @NotNull(message = "A quantidade em estoque é obrigatoria.")
    @Min(value = 0, message = "A quantidade em estoque deve ser maior ou igaul a 0.")
    private Integer estoque;

    @NotNull(message = "A categoria é obrigatoria")
    @Enumerated(EnumType.STRING)
    private CategoriaProduto categoria;

    public ProdutoModel() {}

    public ProdutoModel(Long id, String name, String descricao, Integer estoque, CategoriaProduto categoria) {
        this.id = id;
        this.name = name;
        this.descricao = descricao;
        this.estoque = estoque;
        this.categoria = categoria;
    }
}
