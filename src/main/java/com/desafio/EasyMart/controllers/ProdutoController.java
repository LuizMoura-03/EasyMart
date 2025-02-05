package com.desafio.EasyMart.controllers;

import com.desafio.EasyMart.dtos.ProdutoDTO;
import com.desafio.EasyMart.services.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {


    private final ProdutoService produtoService;

    @Autowired
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> listarTodos() {
        List<ProdutoDTO> produtos = produtoService.listarTodos();
        return ResponseEntity.ok(produtos);

    }

    @GetMapping("/disponiveis")
    public ResponseEntity<List<ProdutoDTO>> listarProdutosDisponiveis() {
        List<ProdutoDTO> produtosDisponiveis = produtoService.listarProdutosComEstoqueMaiorQue(0);
        return ResponseEntity.ok(produtosDisponiveis);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> buscarPorId(@PathVariable Long id) {
        return produtoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

    @PostMapping
    public ResponseEntity< ProdutoDTO> salvar(@Valid @RequestBody ProdutoDTO produtoDTO) {
        try {
            ProdutoDTO produtoSalvo = produtoService.salvar(produtoDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(produtoSalvo);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/lista")
    public ResponseEntity<List<ProdutoDTO>> SalvarLista(@Valid @RequestBody List<ProdutoDTO> produtosDTO) {
        try {
            List<ProdutoDTO> produtosSalvos = produtoService.salvarTodos(produtosDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(produtosSalvos);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDTO> atualizar(@PathVariable Long id, @Valid @RequestBody ProdutoDTO produtoDTO) {
            ProdutoDTO produtoAtualizado = produtoService.atualizar(id, produtoDTO);
            return ResponseEntity.ok(produtoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        produtoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}

