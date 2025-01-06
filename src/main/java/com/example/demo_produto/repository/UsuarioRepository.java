package com.example.demo_produto.repository;

import com.example.demo_produto.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Usuario findByNome(String nome);
}
