package com.desafio.EasyMart.controllers;

import com.desafio.EasyMart.dtos.ClienteDTO;
import com.desafio.EasyMart.dtos.ProdutoDTO;
import com.desafio.EasyMart.models.ClienteModel;
import com.desafio.EasyMart.repositories.ComprasRepository;
import com.desafio.EasyMart.services.ClienteService;
import com.desafio.EasyMart.services.ComprasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ComprasService comprasService;

    @Autowired
    private ComprasRepository comprasRepository;

    @GetMapping("/{id}/compras")
    public ResponseEntity<List<ProdutoDTO>> listarProdutosComprados(@PathVariable Long id) {
        Optional<ClienteModel> cliente = clienteService.buscarPorId(id);

        if (cliente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<ProdutoDTO> produtosComprados = comprasService.listarProdutosCompradosPorCliente(cliente.get());

        return ResponseEntity.ok(produtosComprados);
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<ClienteDTO> atualizarCliente(@PathVariable String cpf, @RequestBody ClienteDTO clienteDTO) {
        try {
            ClienteDTO clienteAtualizado = clienteService.atualizarCliente(cpf, clienteDTO);
            return ResponseEntity.ok(clienteAtualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

}
