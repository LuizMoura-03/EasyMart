package com.desafio.EasyMart.repositories;

import com.desafio.EasyMart.models.ClienteModel;
import com.desafio.EasyMart.models.ComprasModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ComprasRepository extends JpaRepository<ComprasModel, Long> {
    List<ComprasModel> findByCliente(ClienteModel cliente);
}
