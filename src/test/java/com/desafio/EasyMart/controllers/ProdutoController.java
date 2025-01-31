package com.desafio.EasyMart.controllers;

import com.desafio.EasyMart.dtos.ProdutoDTO;
import com.desafio.EasyMart.enums.CategoriaProduto;
import com.desafio.EasyMart.models.ProdutoModel;
import com.desafio.EasyMart.services.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<ProdutoDTO> salvar(@RequestBody ProdutoDTO produtoDTO) {
        try {
            CategoriaProduto categoriaProduto = CategoriaProduto.valueOf(produtoDTO.getCategoria());
            ProdutoModel produtoModel = new ProdutoModel(
                    produtoDTO.getId(),
                    produtoDTO.getNome(),
                    produtoDTO.getDescricao(),
                    produtoDTO.getPreco(),
                    produtoDTO.getEstoque(),
                    categoriaProduto );

            ProdutoModel produtoSalvo = produtoService.salvar(produtoModel);
            ProdutoDTO produtoSalvoDTO = new ProdutoDTO(
                    produtoSalvo.getId(),
                    produtoSalvo.getNome(),
                    produtoSalvo.getDescricao(),
                    produtoSalvo.getPreco(),
                    produtoSalvo.getEstoque(),
                    produtoSalvo.getCategoria().toString()
            );
            return ResponseEntity.ok(produtoSalvoDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

}
