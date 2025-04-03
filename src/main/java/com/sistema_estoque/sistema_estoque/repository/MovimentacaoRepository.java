package com.sistema_estoque.sistema_estoque.repository;

import com.sistema_estoque.sistema_estoque.entity.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {
}
