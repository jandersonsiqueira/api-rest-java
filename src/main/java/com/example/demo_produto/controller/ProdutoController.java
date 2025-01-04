package com.example.demo_produto.controller;

import com.example.demo_produto.model.Produto;
import com.example.demo_produto.service.ProdutoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	@GetMapping
	public ResponseEntity<List<Produto>> getAllProdutos() {
		return ResponseEntity.ok(produtoService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Produto> getProdutoById(@PathVariable Long id) {
		Optional<Produto> produto = produtoService.findById(id);
		return produto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<Produto> createProduto(@RequestBody Produto produto) {
		Produto savedProduto = produtoService.save(produto);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedProduto);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Produto> updateProduto(@PathVariable Long id, @RequestBody Produto produto) {
		return produtoService.findById(id)
				.map(p -> {
					p.setNome(produto.getNome());
					p.setAtivo(produto.isAtivo());
					p.setSku(produto.getSku());
					p.setValorCusto(produto.getValorCusto());
					p.setIcms(produto.getIcms());
					p.setValorVenda(produto.getValorVenda());
					p.setQuantidadeEmEstoque(produto.getQuantidadeEmEstoque());
					p.setCategoriaId(produto.getCategoriaId());
					p.setUsuarioId(produto.getUsuarioId());
					Produto updatedProduto = produtoService.update(p);
					return ResponseEntity.ok(updatedProduto);
				})
				.orElseGet(() -> ResponseEntity.notFound().build());
	}


	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProduto(@PathVariable Long id) {
		produtoService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PatchMapping("/{id}/status")
	public ResponseEntity<Produto> alterarStatusProduto(@PathVariable Long id, @RequestBody Map<String, Boolean> request) {
		Boolean ativo = request.get("ativo");
		if (ativo == null) {
			return ResponseEntity.badRequest().build();
		}
		return produtoService.findById(id)
				.map(produto -> {
					produto.setAtivo(ativo);
					Produto produtoAtualizado = produtoService.update(produto);
					return ResponseEntity.ok(produtoAtualizado);
				})
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

}