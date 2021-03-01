package com.hahn.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Usuario {
	
	@Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="username", nullable=false, unique=true, length=20)
	private String username;
	
	@Column(name="name", length=20)
	private String name;
	 
	@Column(name="password", length=100)
	private String password;

	@OneToMany( cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tarjeta> cards = new ArrayList<>();
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Tarjeta> getCards() {
		return cards;
	}

	public void setCards(List<Tarjeta> cards) {
		this.cards = cards;
	}
	
}
