package com.hahn.backend;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.hahn.model.Usuario;
import com.hahn.repo.IUsuarioRepo;

//@RunWith(SpringRunner.class)
@SpringBootTest
class BackendApplicationTests {
	
	@Autowired
	private IUsuarioRepo repo;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	@Test
	public void crearUsuarioText() {
		Usuario us = new Usuario();
		
		us.setId(1);
		us.setUsername("Nuevo");
		us.setPassword(encoder.encode("123"));
		
		Usuario retorno = repo.save(us);
		
		assertTrue(retorno.getPassword().equalsIgnoreCase(us.getPassword()));
		
	}

}
