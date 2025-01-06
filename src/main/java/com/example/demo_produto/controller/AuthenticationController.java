package com.example.demo_produto.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.example.demo_produto.config.AuthenticationRequest;
import com.example.demo_produto.config.AuthenticationResponse;
import com.example.demo_produto.config.JwtUtil;
import com.example.demo_produto.config.MyUserDetailsService;

@RestController
public class AuthenticationController {

	private final AuthenticationManager authenticationManager;
	private final JwtUtil jwtUtil;
	private final MyUserDetailsService userDetailsService;

	public AuthenticationController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, MyUserDetailsService userDetailsService) {
		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;
		this.userDetailsService = userDetailsService;
	}

	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
		try {
			// Tente autenticar o usuário usando a senha codificada
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getNome(), authenticationRequest.getSenha())
			);
		} catch (BadCredentialsException e) {
			// Retorna erro quando a senha está incorreta ou o usuário não existe
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário inexistente ou senha inválida");
		}

		// Se a autenticação for bem-sucedida
		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getNome());

		final String jwt = jwtUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}

}

