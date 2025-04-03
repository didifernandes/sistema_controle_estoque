package com.sistema_estoque.sistema_estoque.service;

import com.sistema_estoque.sistema_estoque.entity.Movimentacao;
import com.sistema_estoque.sistema_estoque.entity.Produto;
import com.sistema_estoque.sistema_estoque.entity.TipoMovimentacao;
import com.sistema_estoque.sistema_estoque.repository.MovimentacaoRepository;
import com.sistema_estoque.sistema_estoque.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MovimentacaoService {

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    @Autowired
    private ProdutoService produtoService;
    @Autowired
    private ProdutoRepository produtoRepository;

    @Transactional
    public Movimentacao registrarMovimentacao(Movimentacao movimentacao) {
        validarMovimentacao(movimentacao);
        atualizarEstoque(movimentacao);

        movimentacao.setData(LocalDateTime.now());
        return movimentacaoRepository.save(movimentacao);
    }

    public List<Movimentacao> listarTodas(){
        return movimentacaoRepository.findAll();
    }

    public Optional<Movimentacao> buscarPorId(Long id){
        return movimentacaoRepository.findById(id);
    }

    private void validarMovimentacao(Movimentacao movimentacao) {
        if (movimentacao.getProduto() == null || movimentacao.getProduto().getId() == null) {
            throw new IllegalArgumentException("O produto da movimentação desse ser informado.");
        }

        if (movimentacao.getQuantidade() == null || movimentacao.getQuantidade() <= 0) {
            throw new IllegalArgumentException("A quantidade tem que ser maior que zero.");
        }
    }

    private void atualizarEstoque(Movimentacao movimentacao) {
        Produto produto = produtoRepository
                .findById(movimentacao.getProduto().getId())
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado."));

        if (movimentacao.getTipo() == TipoMovimentacao.SAIDA && produto.getQuantidade() < movimentacao.getQuantidade()) {
            throw new IllegalArgumentException("Estoque insuficiente para a saída de produtos.");
        }

        int novaQuantidade = calcularNovaQuantidade(produto, movimentacao);
        produto.setQuantidade(novaQuantidade);
        produtoRepository.save(produto);
        }

        private int calcularNovaQuantidade(Produto produto, Movimentacao movimentacao){
            return movimentacao.getTipo() == TipoMovimentacao.ENTRADA
                    ? produto.getQuantidade() + movimentacao.getQuantidade()
                    : produto.getQuantidade() - movimentacao.getQuantidade();
        }

    }




