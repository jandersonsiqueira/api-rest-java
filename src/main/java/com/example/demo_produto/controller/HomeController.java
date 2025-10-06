package com.example.demo_produto.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@GetMapping("/aplicacao-inicial")
	public String home() {
		return "Bem-vindo à aplicação!";
	}
}
