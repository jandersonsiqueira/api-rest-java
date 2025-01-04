package com.example.demo_produto.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "produto")
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String nome;

	@Column(nullable = false)
	private boolean ativo;

	@Column(nullable = false, unique = true)
	private String sku;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "categoria_id", nullable = true)
	private Categoria categoria;

	@Column(nullable = false, precision = 10, scale = 2)
	private BigDecimal valorCusto;

	@Column(nullable = false, precision = 5, scale = 2)
	private BigDecimal icms;

	@Column(nullable = false, precision = 10, scale = 2)
	private BigDecimal valorVenda;

	@Column(nullable = false)
	private LocalDateTime dataCadastro;

	@Column(nullable = false)
	private int quantidadeEmEstoque;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuario_id", nullable = true)
	private Usuario usuario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	@JsonProperty("categoria_id")
	public Long getCategoriaId() {
		return categoria != null ? categoria.getId() : null;
	}

	public void setCategoriaId(Long categoriaId) {
		if (categoriaId != null) {
			this.categoria = new Categoria();
			this.categoria.setId(categoriaId);
		} else {
			this.categoria = null;
		}
	}

	public BigDecimal getValorCusto() {
		return valorCusto;
	}

	public void setValorCusto(BigDecimal valorCusto) {
		this.valorCusto = valorCusto;
	}

	public BigDecimal getIcms() {
		return icms;
	}

	public void setIcms(BigDecimal icms) {
		this.icms = icms;
	}

	public BigDecimal getValorVenda() {
		return valorVenda;
	}

	public void setValorVenda(BigDecimal valorVenda) {
		this.valorVenda = valorVenda;
	}

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public int getQuantidadeEmEstoque() {
		return quantidadeEmEstoque;
	}

	public void setQuantidadeEmEstoque(int quantidadeEmEstoque) {
		this.quantidadeEmEstoque = quantidadeEmEstoque;
	}

	@JsonProperty("usuario_id")
	public Long getUsuarioId() {
		return usuario != null ? usuario.getId() : null;
	}

	public void setUsuarioId(Long usuarioId) {
		if (usuarioId != null) {
			this.usuario = new Usuario();
			this.usuario.setId(usuarioId);
		} else {
			this.usuario = null;
		}
	}
}
