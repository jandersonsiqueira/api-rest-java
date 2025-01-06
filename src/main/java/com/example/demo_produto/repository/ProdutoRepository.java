package com.example.demo_produto.repository;

import com.example.demo_produto.model.Produto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	Page<Produto> findAll(Pageable pageable);
}