package com.hahn.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hahn.model.Usuario;

public interface IUsuarioRepo extends JpaRepository<Usuario, Integer> {
	
	Usuario findByUsername(String username);
	
}
