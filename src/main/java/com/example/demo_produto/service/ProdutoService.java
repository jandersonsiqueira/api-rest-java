package com.example.demo_produto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.example.demo_produto.model.Produto;
import com.example.demo_produto.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;


	public Page<Produto> findAll(Pageable pageable) {
		return produtoRepository.findAll(pageable);
	}

	public Optional<Produto> findById(Long id) {
		return produtoRepository.findById(id);
	}

	public Produto save(Produto produto) {
		produto.setDataCadastro(LocalDateTime.now());
		return produtoRepository.save(produto);
	}

	public void delete(Long id) {
		produtoRepository.deleteById(id);
	}

	public Produto update(Produto produto){
		return produtoRepository.save(produto);
	}
}