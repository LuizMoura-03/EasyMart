package com.desafio.EasyMart.dtos;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public class ComprasDTO {

    @NotBlank(message = "O CPF é obrigatório.")
    private String cpf;

    private List<ProdutoCompraDTO> produtos;


    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<ProdutoCompraDTO> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<ProdutoCompraDTO> produtos) {
        this.produtos = produtos;
    }

    public static class ProdutoCompraDTO {
        @NotBlank(message = "O nome do produto é obrigatório.")
        private String nome;

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }
    }
}
