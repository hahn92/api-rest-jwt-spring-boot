package com.hahn.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hahn.model.Tarjeta;

public interface ITarjetaRepo extends JpaRepository<Tarjeta, Integer> {

}
