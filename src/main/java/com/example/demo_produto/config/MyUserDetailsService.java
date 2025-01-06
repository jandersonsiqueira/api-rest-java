package com.example.demo_produto.config;

import java.util.ArrayList;

import com.example.demo_produto.model.Usuario;
import com.example.demo_produto.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

	private final UsuarioRepository usuarioRepository;

	public MyUserDetailsService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String nome) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByNome(nome);

		if (usuario == null) {
			throw new UsernameNotFoundException("Usuário não encontrado: " + nome);
		}

		return new User(usuario.getNome(), usuario.getSenha(), new ArrayList<>());
	}
}
