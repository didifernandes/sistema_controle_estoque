package com.sistema_estoque.sistema_estoque.repository;

import com.sistema_estoque.sistema_estoque.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
