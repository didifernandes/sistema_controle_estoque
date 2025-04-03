package com.sistema_estoque.sistema_estoque.service;

import com.sistema_estoque.sistema_estoque.entity.Produto;
import com.sistema_estoque.sistema_estoque.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    public Optional<Produto> buscarPorId(Long id) {
        return produtoRepository.findById(id);
    }

    public Produto salvar(Produto produto) {
        validarProduto(produto);
        return produtoRepository.save(produto);
    }

    public Produto atualizar(Long id, Produto produto) {
        if (!produtoRepository.existsById(produto.getId())) {
            throw new IllegalArgumentException("Produto não encontrado.");
        }

        produto.setId(id);
        validarProduto(produto);
        return produtoRepository.save(produto);
    }

    public void deletar(Long id) {
        if (!produtoRepository.existsById(id)) {
            throw new IllegalArgumentException("Produto não encontrado.");
        }
        produtoRepository.deleteById(id);
    }

    private void validarProduto(Produto produto) {
        if (produto.getNome() == null || produto.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do produto é obrigatório.");
        }

        if (produto.getPreco() == null || produto.getPreco().doubleValue() <= 0) {
            throw new IllegalArgumentException("O preço deve ser maior que zero.");
        }

        if (produto.getQuantidade() == null || produto.getQuantidade() < 0){
            throw new IllegalArgumentException("O quantidade não pode ser negativa.");
        }


    }
}
