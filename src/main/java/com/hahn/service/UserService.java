package com.hahn.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hahn.dto.UsuarioDTO;
import com.hahn.model.Usuario;
import com.hahn.repo.IUsuarioRepo;


@Service
public class UserService implements UserDetailsService{
	
	@Autowired
	private IUsuarioRepo repo;
	
	public UserService(IUsuarioRepo repo) {
        this.repo = repo;
    }
	
	public UsuarioDTO getUser(String username) {
		Usuario us = repo.findByUsername(username);
		UsuarioDTO usd = new UsuarioDTO(us.getUsername(), us.getName(), us.getCards());
        return usd;
    }
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario us = repo.findByUsername(username);
		
		List<GrantedAuthority> roles = new ArrayList<>();
		roles.add(new SimpleGrantedAuthority("ADMIN"));
		
		UserDetails userDet = new User(us.getUsername(), us.getPassword(), roles);
		
		return userDet;
		 
	}

}
