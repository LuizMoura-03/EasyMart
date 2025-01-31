package com.desafio.EasyMart.repositories;

import com.desafio.EasyMart.models.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<ClienteModel, Long> {
}
