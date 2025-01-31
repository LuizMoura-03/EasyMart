package com.desafio.EasyMart.controllers;

import com.desafio.EasyMart.dtos.ProdutoDTO;
import com.desafio.EasyMart.models.ProdutoModel;
import com.desafio.EasyMart.services.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> listarTodos() {
        List<ProdutoDTO> produtos = produtoService.listarTodos().stream()
                .map(produto -> new ProdutoDTO(
                        produto.getId(),
                        produto.getNome(),
                        produto.getDescricao(),
                        null,
                        produto.getEstoque(),
                        produto.getCategoria().toString() // Convertendo CategoriaProduto para String
                )).toList();
        return ResponseEntity.ok(produtos);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> buscarPorId(@PathVariable Long id) {
        Optional<ProdutoModel> produtoModel = produtoService.buscarPorId(id);
        if (produtoModel.isPresent()) {
            ProdutoDTO produtoDTO = new ProdutoDTO(
                    produtoModel.get().getId(),
                    produtoModel.get().getNome(),
                    produtoModel.get().getDescricao(),
                    null,
                    produtoModel.get().getEstoque(),
                    produtoModel.get().getCategoria().toString()
            );
            return ResponseEntity.ok(produtoDTO);
        }
        return ResponseEntity.notFound().build();
    }

}
