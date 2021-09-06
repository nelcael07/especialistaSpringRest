package com.example.demo.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import com.example.demo.domain.exception.RestauranteNaoEncontradoException;
import com.example.demo.domain.model.Restaurante;
import com.example.demo.domain.repository.RestauranteRepository;

@Service
public class CadastroRestauranteService {
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	public Restaurante salvar(Restaurante restaurante) {
		return restauranteRepository.salvar(restaurante);
	}
	
	public void remover(Long id) {
		try {
			restauranteRepository.remover(id);
		} catch (EmptyResultDataAccessException e) {
			throw new RestauranteNaoEncontradoException(String.format("Restaurante %d não encontrado", id));
		}
	}
	
	
	
}
