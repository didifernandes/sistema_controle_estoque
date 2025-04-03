package com.sistema_estoque.sistema_estoque.controller;

import com.sistema_estoque.sistema_estoque.entity.Movimentacao;
import com.sistema_estoque.sistema_estoque.service.MovimentacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movimentacoes")
public class MovimentacaoController {

    @Autowired
    private MovimentacaoService movimentacaoService;

    @PostMapping
    public Movimentacao registrar(@RequestBody Movimentacao movimentacao) {
        return movimentacaoService.registrarMovimentacao(movimentacao);
    }

    @GetMapping
    public List<Movimentacao> listarTodas() {
        return movimentacaoService.listarTodas();
    }

    @GetMapping("/{id}")
    public Optional<Movimentacao> buscarPorId(@PathVariable Long id) {
        return movimentacaoService.buscarPorId(id);
    }

}
