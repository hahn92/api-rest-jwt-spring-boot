package com.hahn.dto;

import java.util.ArrayList;
import java.util.List;

import com.hahn.model.Tarjeta;

public class UsuarioDTO {

	private int userID;
	
	private String username;
	
	private String name;
	
	private List<Tarjeta> cards = new ArrayList<>();
	
	public UsuarioDTO(String username, String name, List<Tarjeta> cards) {
		super();
		this.username = username;
		this.name = name;
		this.cards = cards;
	}
	
	public int getUserID() {
		return userID;
	}
	
	public void setUserID(int userID) {
		this.userID = userID;
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

	public List<Tarjeta> getCards() {
		return cards;
	}

	public void setCards(List<Tarjeta> cards) {
		this.cards = cards;
	}
	
}
