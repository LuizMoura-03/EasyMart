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
    private String nome;

    @Size(max = 500, message = "A descrição não pode ultrapassar os 500 carateres.")
    private String descricao;

    @NotNull(message = "A quantidade em estoque é obrigatoria.")
    @Min(value = 0, message = "A quantidade em estoque deve ser maior ou igaul a 0.")
    private Integer estoque;

    @NotNull(message = "A categoria é obrigatoria")
    @Enumerated(EnumType.STRING)
    private CategoriaProduto categoria;

    public ProdutoModel() {}

    public ProdutoModel(Long id, String nome, String descricao, Integer estoque, CategoriaProduto categoria) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.estoque = estoque;
        this.categoria = categoria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getEstoque() {
        return estoque;
    }

    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }

    public CategoriaProduto getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaProduto categoria) {
        this.categoria = categoria;
    }
}
