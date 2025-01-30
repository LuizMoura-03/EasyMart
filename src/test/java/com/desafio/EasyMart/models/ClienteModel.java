package com.desafio.EasyMart.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

@Entity
@Table(name = "clientes")
public class ClienteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é obrigatorio.")
    @Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres.")
    private String nome;

    @NotBlank(message = "O CPF é obrigatorio.")
    @CPF(message = "CPF invalido")
    @Column(unique = true)
    private String cpf;

    @NotBlank(message = "O email é obrigatorio")
    @Email(message = "O email deve ser valido.")
    private String emai;

    public ClienteModel() {}

    public ClienteModel(Long id, String nome, String cpf, String emai) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.emai = emai;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmai() {
        return emai;
    }

    public void setEmai(String emai) {
        this.emai = emai;
    }
}
