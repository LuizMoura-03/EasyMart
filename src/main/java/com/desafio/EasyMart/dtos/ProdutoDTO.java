package com.desafio.EasyMart.dtos;

import jakarta.validation.constraints.*;

public class ProdutoDTO {
    private Long id;

    @NotBlank(message = "O nome é obrigatorio.")
    @Size(min = 2, max = 100, message = "o nome deve ter entre 2 e 100 Caracteres.")
    private String nome;

    @Size(max = 500, message = "A descrição não pode ultrapassar os 500 caracteres.")
    private String descricao;

    @NotNull(message = "O preço é obrigatorio.")
    @DecimalMin(value = "0.01", message = "O preço deve ser maior que 0.")
    private Double preco;

    @NotNull(message = "A quantidade é obirgatoria.")
    @Min(value = 0, message = "A quantidade em estoque deve ser maior ou igual a 0.")
    private Integer estoque;

    @NotNull(message = "A categoria é obirgatoria.")
    private String categoria;

    public ProdutoDTO() {}

    public ProdutoDTO(Long id, String nome, String descricao, Double preco, Integer estoque, String categoria) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
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

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Integer getEstoque() {
        return estoque;
    }

    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        categoria = categoria;
    }
}
