package com.desafio.EasyMart.repositories;

import com.desafio.EasyMart.models.ProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<ProdutoModel, Long> {
    boolean existsByNome(String nome);
    Optional<ProdutoModel> findByNome(String nome);
    List<ProdutoModel> findByEstoqueGreaterThan(int quantidade);

}
