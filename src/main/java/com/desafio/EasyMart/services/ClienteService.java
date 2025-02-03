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
        if (clienteRepository.existsByCpf(clienteDTO.getCpf())) {
            throw new IllegalArgumentException("Cliente com o mesmo CPF já existe.");
        }
        if (clienteRepository.existsByEmail(clienteDTO.getEmail())) {
            throw new IllegalArgumentException("Cliente com o mesmo email já existe.");
        }
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

    public ClienteDTO atualizarCliente(String cpf, ClienteDTO clienteDTO) {
        // Verifica se o cliente existe pelo CPF
        ClienteModel clienteExistente = clienteRepository.findByCpf(cpf)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado com o CPF fornecido."));

        // Atualiza os dados do cliente
        clienteExistente.setNome(clienteDTO.getNome());
        clienteExistente.setEmail(clienteDTO.getEmail());
        clienteExistente.setCpf(clienteDTO.getCpf()); // CPF pode ser atualizado, se necessário
        clienteExistente.setEndereco(clienteDTO.getEndereco());

        // Salva as alterações no banco de dados
        ClienteModel clienteAtualizado = clienteRepository.save(clienteExistente);

        // Retorna o cliente atualizado como DTO
        return new ClienteDTO(
                clienteAtualizado.getId(),
                clienteAtualizado.getNome(),
                clienteAtualizado.getEmail(),
                clienteAtualizado.getCpf(),
                clienteAtualizado.getEndereco()
        );
    }

    public void deletarCliente(Long id) {
        clienteRepository.deleteById(id);
    }

}
