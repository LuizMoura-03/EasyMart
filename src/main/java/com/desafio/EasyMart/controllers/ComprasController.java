package com.desafio.EasyMart.controllers;

import com.desafio.EasyMart.dtos.ComprasDTO;
import com.desafio.EasyMart.models.ProdutoModel;
import com.desafio.EasyMart.services.ComprasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/compras")
public class ComprasController {

    @Autowired
    private ComprasService comprasService;

    @PostMapping
    public ResponseEntity<List<ProdutoModel>> registrarCompra(@RequestBody ComprasDTO comprasDTO) {
        try {
            // Chama o serviço e obtem a lista de produtos comprados
            List<ProdutoModel> produtosComprados = comprasService.registrarCompra(comprasDTO);

            return ResponseEntity.ok(produtosComprados);
        } catch (IllegalArgumentException e) {

            return ResponseEntity.badRequest().body(null);
        }
    }
}
