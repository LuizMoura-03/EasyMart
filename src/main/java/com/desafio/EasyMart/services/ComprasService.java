package com.desafio.EasyMart.services;

import com.desafio.EasyMart.dtos.ComprasDTO;
import com.desafio.EasyMart.dtos.ProdutoDTO;
import com.desafio.EasyMart.models.ClienteModel;
import com.desafio.EasyMart.models.ComprasModel;
import com.desafio.EasyMart.models.ProdutoModel;
import com.desafio.EasyMart.repositories.ClienteRepository;
import com.desafio.EasyMart.repositories.ComprasRepository;
import com.desafio.EasyMart.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ComprasService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ComprasRepository comprasRepository;

    public List<ProdutoModel> registrarCompra(ComprasDTO comprasDTO) {
        String cpf = comprasDTO.getCpf();
        List<ComprasDTO.ProdutoCompraDTO> produtos = comprasDTO.getProdutos();

        // Verificar se o cliente existe
        Optional<ClienteModel> clienteOptional = clienteRepository.findByCpf(cpf);
        if (clienteOptional.isEmpty()) {
            throw new IllegalArgumentException ("Cliente não encontrado.");
        }
        ClienteModel cliente = clienteOptional.get();

        // Verificar se os produtos existem
        List<ProdutoModel> produtosComprados = new ArrayList<>();
        List<String> produtosEmFalta = new ArrayList<>();

        for (ComprasDTO.ProdutoCompraDTO produtoDTO : produtos) {
            String nomeProduto = produtoDTO.getNome();
            Optional<ProdutoModel> produtoOptional = produtoRepository.findByNome(nomeProduto);
            if (produtoOptional.isEmpty()) {
                throw new IllegalArgumentException ("Produto não encontrado: " + nomeProduto);
            }
            ProdutoModel produto = produtoOptional.get();
            if (produto.getEstoque() <= 0) {
                produtosEmFalta.add(produto.getNome());
            } else {
                produtosComprados.add(produto);
            }
        }

        if (!produtosEmFalta.isEmpty()) {
            throw new IllegalArgumentException("Produto(s) em falta: " + String.join(", ", produtosEmFalta));
        }

        // Atualizar o estoque dos produtos
        for (ProdutoModel produto : produtosComprados) {
            produto.setEstoque(produto.getEstoque() - 1);
            produtoRepository.save(produto);
        }

        // Registrar a compra
        ComprasModel compra = new ComprasModel(cliente, produtosComprados);
        comprasRepository.save(compra);

        return produtosComprados;
    }

    public List<ProdutoDTO> listarProdutosCompradosPorCliente(ClienteModel cliente) {
        // Buscar todas as compras do cliente
        List<ComprasModel> compras = comprasRepository.findByCliente(cliente);

        // Extrair os produtos das compras e convertê-los para ProdutoDTO
        return compras.stream()
                .flatMap(compra -> compra.getProdutos().stream())
                .map(produto -> new ProdutoDTO(
                        produto.getId(),
                        produto.getNome(),
                        produto.getDescricao(),
                        produto.getPreco(),
                        produto.getEstoque(),
                        produto.getCategoria().toString()
                ))
                .collect(Collectors.toList());
    }

}


