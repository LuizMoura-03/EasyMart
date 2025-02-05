package com.desafio.EasyMart.services;

import com.desafio.EasyMart.dtos.ProdutoDTO;
import com.desafio.EasyMart.enums.CategoriaProduto;
import com.desafio.EasyMart.models.ProdutoModel;
import com.desafio.EasyMart.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<ProdutoDTO> listarTodos() {
        return produtoRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());

    }

    public Optional<ProdutoDTO> buscarPorId(Long id) {
        return produtoRepository.findById(id).map(this::toDTO);
    }

    public ProdutoDTO salvar(ProdutoDTO produtoDTO) {
        validarProdutoUnico(produtoDTO.getNome());
        ProdutoModel produtoModel = toModel(produtoDTO);
        ProdutoModel produtoSalvo = produtoRepository.save(produtoModel);
        return toDTO(produtoSalvo);
    }

    public List<ProdutoDTO> salvarTodos(List<ProdutoDTO> produtosDTO) {
        return produtosDTO.stream()
                .map(this::salvar) // Reutiliza o método salvar para cada produto
                .collect(Collectors.toList());
    }

    public ProdutoDTO atualizar(Long id, ProdutoDTO produtoDTO) {
        ProdutoModel produtoExistente = buscarProdutoPorId(id);
        ProdutoModel produtoAtualizado = toModel(produtoDTO);
        produtoAtualizado.setId(produtoExistente.getId()); // Garante que o ID não será alterado
        ProdutoModel produtoSalvo = produtoRepository.save(produtoAtualizado);
        return toDTO(produtoSalvo);

    }

    public ProdutoDTO atualizarEstoque(Long id, int quantidade) {
        ProdutoModel produto = buscarProdutoPorId(id);
        produto.setEstoque(produto.getEstoque() + quantidade);
        ProdutoModel produtoAtualizado = produtoRepository.save(produto);
        return toDTO(produtoAtualizado);
    }

    public List<ProdutoDTO> listarProdutosComEstoqueMaiorQue(int quantidade) {
        return produtoRepository.findByEstoqueGreaterThan(quantidade).stream()
                .map(this::toDTO).collect(Collectors.toList());
    }

    public List<ProdutoDTO> listarPorCategoria(CategoriaProduto categoria) {
        return produtoRepository.findByCategoria(categoria).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public void deletar(Long id) {
        ProdutoModel produto = buscarProdutoPorId(id);
        produtoRepository.delete(produto);
    }

    private ProdutoModel buscarProdutoPorId(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto com o ID" + id + "fornecido não existe."));
    }

    private void validarProdutoUnico(String nome) {
        if (produtoRepository.existsByNome(nome)) {
            throw new IllegalArgumentException("já existe um produto com o mesmo nome: " + nome);
        }
    }

    // Método para conversão
    private ProdutoDTO toDTO(ProdutoModel produtoModel) {
        return new ProdutoDTO(
                produtoModel.getId(),
                produtoModel.getNome(),
                produtoModel.getDescricao(),
                produtoModel.getPreco(),
                produtoModel.getEstoque(),
                produtoModel.getCategoria().toString()
        );
    }

    private ProdutoModel toModel(ProdutoDTO produtoDTO) {
        ProdutoModel produtoModel = new ProdutoModel();
                produtoModel.setNome(produtoDTO.getNome());
                produtoModel.setDescricao(produtoDTO.getDescricao());
                produtoModel.setPreco(produtoDTO.getPreco());
                produtoModel.setEstoque(produtoDTO.getEstoque());
                produtoModel.setCategoria(toEnum(produtoDTO.getCategoria()));
                return produtoModel;
    }

    private CategoriaProduto toEnum(String categoria) {
        try {
            return CategoriaProduto.valueOf(categoria.toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new IllegalArgumentException("Categoria inválida: " + categoria);
        }
    }
}

