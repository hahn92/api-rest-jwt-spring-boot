package com.hahn.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hahn.config.ResponseHandler;
import com.hahn.dto.UsuarioDTO;
import com.hahn.model.Usuario;
import com.hahn.repo.IUsuarioRepo;
import com.hahn.service.UserService;


@RestController
@RequestMapping("/api/v1/user")
public class UserController {
	
	@Autowired
	private IUsuarioRepo repo;
	
	@Autowired
	private UserService service;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@PostMapping("/sing-up")
	public void UserCreate(Usuario per) {
		per.setPassword(bCryptPasswordEncoder.encode(per.getPassword()));
		repo.save(per);
	}
	
	@GetMapping
	public ResponseEntity<Object> UserRead(Authentication auth) {
		UsuarioDTO user = service.getUser(auth.getPrincipal() + "");
		return ResponseHandler.generateResponse(HttpStatus.OK, true, "Success", user);
	}
	
	@PutMapping
	public void UserUpdate(Usuario per) {
		repo.save(per);
	}
	
	/*
	@DeleteMapping(value = "/{id}")
	public void UserDelete(@PathVariable("id") Integer id) {
		repo.deleteById(id);
	}
	*/

}
