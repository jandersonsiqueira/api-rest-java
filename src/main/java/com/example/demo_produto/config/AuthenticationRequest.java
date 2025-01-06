package com.example.demo_produto.config;

public class AuthenticationRequest {

	private String nome;
	private String senha;

	public AuthenticationRequest() {
	}

	public AuthenticationRequest(String nome, String senha) {
		this.nome = nome;
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
