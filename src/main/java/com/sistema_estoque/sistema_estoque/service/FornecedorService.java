package com.sistema_estoque.sistema_estoque.service;

import com.sistema_estoque.sistema_estoque.entity.Fornecedor;
import com.sistema_estoque.sistema_estoque.repository.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FornecedorService {

    @Autowired
    private FornecedorRepository fornecedorRepository;

    public List<Fornecedor> listarTodos(){
        return fornecedorRepository.findAll();
    }

    public Optional<Fornecedor> buscarPorId(Long id){
        return fornecedorRepository.findById(id);
    }

    public Fornecedor salvar(Fornecedor fornecedor){
        return fornecedorRepository.save(fornecedor);
    }

    public void  deletar(Long id){
        fornecedorRepository.deleteById(id);
    }
}
