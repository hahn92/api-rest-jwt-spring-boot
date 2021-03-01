package com.hahn.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hahn.model.Tarjeta;
import com.hahn.model.Usuario;
import com.hahn.repo.ITarjetaRepo;
import com.hahn.repo.IUsuarioRepo;


@RestController
@RequestMapping("/api/v1/card")
public class TarjetaController {
	@Autowired
	private ITarjetaRepo repo;
	
	@Autowired
	private IUsuarioRepo repou;
	
	@PostMapping
	public void CardCreate(Tarjeta tar, Authentication auth) {
		Usuario us = repou.findByUsername(auth.getPrincipal() + "");
		List<Tarjeta> li = us.getCards();
		li.add(tar);
		repo.save(tar);
	}
	
	@PutMapping
	public void CardUpdate(Tarjeta tar, Authentication auth) {
		repo.save(tar);
	}

}
