package com.desafio.EasyMart.models;

import com.desafio.EasyMart.enums.CategoriaProduto;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "product")
public class ProdutoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O campo 'nome' é obrigatorio")
    @Size(min = 2, max = 100, message = "O nome deve ter enre 2 e 100 caracteres.")
    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Size(max = 500, message = "O campo 'descrição' não pode ultrapassar os 500 carateres.")
    @Column(name = "descricao", length = 500)
    private String descricao;

    @NotNull(message = "O campo 'preço' é obrigatório.")
    @DecimalMin(value = "0.01", message = "O campo 'preço' deve ser maior que 0.")
    @Column(name = "preco", nullable = false)
    private Double preco;

    @NotNull(message = "O campo estoque' é obrigatoria.")
    @Min(value = 0, message = "O campo 'estoque' deve ser maior ou igaul a 0.")
    private Integer estoque;

    @NotNull(message = "O campo 'categoria' é obrigatoria")
    @Enumerated(EnumType.STRING)
    @Column(name = "categoria", nullable = false)
    private CategoriaProduto categoria;

    public ProdutoModel() {}

    public ProdutoModel(Long id, String nome, String descricao,Double preco, Integer estoque, CategoriaProduto categoria) {
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

    public Double getPreco() { // Novo método
        return preco;
    }

    public void setPreco(Double preco) { // Novo método
        this.preco = preco;
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

    @Override
    public String toString() {
        return "ProdutoModel{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", preco=" + preco +
                ", estoque=" + estoque +
                ", categoria=" + categoria +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProdutoModel that = (ProdutoModel) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
