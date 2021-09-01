package com.example.demo.domain.repository;

import java.util.List;

import com.example.demo.domain.model.Cozinha;

public interface CozinhaRespository {
	
	List<Cozinha> listar();
	Cozinha buscar(Long id);
	Cozinha salvar(Cozinha cozinha);
	void remover(Cozinha cozinha);
	
}
