package com.example.demo_produto.model;

import jakarta.persistence.*;

@Entity
@Table(name = "categoria")
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String nome;

	@Column(nullable = false)
	private boolean ativo;

	@Column(nullable = false)
	private String tipo;

	public Categoria() {
	}

	public Categoria(Long id, String nome, boolean ativo, String tipo) {
		this.id = id;
		this.nome = nome;
		this.ativo = ativo;
		this.tipo = tipo;
	}

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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}