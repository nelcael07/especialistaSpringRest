package com.example.demo.domain.repository;

import java.util.List;

import com.example.demo.domain.model.Cozinha;

public interface CozinhaRespository {
	
	List<Cozinha> listar();
	List<Cozinha> consultarPorNome(String nome);
	Cozinha buscar(Long id);
	Cozinha salvar(Cozinha cozinha);
	void remover(Long id);
	
}
