package com.sistema_estoque.sistema_estoque.controller;

import com.sistema_estoque.sistema_estoque.entity.Fornecedor;
import com.sistema_estoque.sistema_estoque.service.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/fornecedores")
public class FornecedorController {

    @Autowired
    private FornecedorService fornecedorService;

    @GetMapping
    public List<Fornecedor> listarTodos() {
        return fornecedorService.listarTodos();
    }

    @GetMapping("/{id}")
    public Optional<Fornecedor> buscarPorId(@PathVariable Long id) {
        return fornecedorService.buscarPorId(id);
    }

    @PostMapping
    public Fornecedor cadastrar(@RequestBody Fornecedor fornecedor) {
        return fornecedorService.salvar(fornecedor);
    }

    @DeleteMapping
    public void remover(@PathVariable Long id) {
        fornecedorService.deletar(id);
    }

}
