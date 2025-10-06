package com.example.demo_produto.controller;

import com.example.demo_produto.model.Produto;
import com.example.demo_produto.service.ProdutoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	@GetMapping
	@Operation(
			summary = "Listar produtos com paginação e ordenação",
			description = "Este endpoint permite listar os produtos com paginação e ordenação. A requisição pode incluir parâmetros de paginação e ordenação, como exemplo: http://localhost:8080/produto?page=0&size=10&sort=id,asc"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Produtos listados com sucesso"),
			@ApiResponse(responseCode = "400", description = "Erro nos parâmetros de paginação ou ordenação")
	})
	public ResponseEntity<Page<Produto>> getAllProdutos(
			@Parameter(description = "Número da página (começa em 0)", example = "0")
			@RequestParam(defaultValue = "0") int page,
			@Parameter(description = "Quantidade de itens por página", example = "10")
			@RequestParam(defaultValue = "10") int size,
			@Parameter(description = "Campo e direção para ordenação (exemplo: nome,asc ou nome,desc)", example = "id,asc")
			@RequestParam(defaultValue = "id,asc") String sort
	) {
		try {
			String[] sortParams = sort.split(",");
			String sortField = sortParams[0];
			Sort.Direction sortDirection = Sort.Direction.fromString(sortParams[1]);

			Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortField));

			Page<Produto> produtos = produtoService.findAll(pageable);
			return ResponseEntity.ok(produtos);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Produto> getProdutoById(@PathVariable Long id) {
		Optional<Produto> produto = produtoService.findById(id);
		return produto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<List<Produto>> createProdutos(@RequestBody List<Produto> produtos) {
		List<Produto> savedProdutos = produtoService.saveAll(produtos);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedProdutos);
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
