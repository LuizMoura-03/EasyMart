package com.desafio.EasyMart.repositories;

import com.desafio.EasyMart.models.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<ClienteModel, Long> {
    Optional<ClienteModel> findByCpf(String cpf);
    boolean existsByCpf(String cpf);
    boolean existsByEmail(String email);

}
