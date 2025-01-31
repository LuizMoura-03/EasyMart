package com.desafio.EasyMart.repositories;

import com.desafio.EasyMart.models.ProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<ProdutoModel, Long> {
    boolean existsByNome(String nome);
}
