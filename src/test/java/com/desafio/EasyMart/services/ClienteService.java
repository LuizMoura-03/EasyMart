package com.desafio.EasyMart.services;

import com.desafio.EasyMart.dtos.ClienteDTO;
import com.desafio.EasyMart.models.ClienteModel;
import com.desafio.EasyMart.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public ClienteModel salvarCliente (ClienteDTO clienteDTO) {
        ClienteModel cliente = new ClienteModel(
                clienteDTO.getNome(),
                clienteDTO.getCpf(),
                clienteDTO.getEmail());
        return clienteRepository.save(cliente);
    }

    public List<ClienteModel> listarClientes() {
        return clienteRepository.findAll();
    }

    public Optional<ClienteModel> buscarPorId(Long id) {
        return clienteRepository.findById(id);
    }

    public void deletarCliente(Long id) {
        clienteRepository.deleteById(id);
    }

}
