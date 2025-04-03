package com.sistema_estoque.sistema_estoque.repository;

import com.sistema_estoque.sistema_estoque.entity.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {
}
