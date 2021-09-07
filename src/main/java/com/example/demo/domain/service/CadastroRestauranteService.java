package com.example.demo.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import com.example.demo.domain.exception.EntidadeNaoEncontradaException;
import com.example.demo.domain.model.Cozinha;
import com.example.demo.domain.model.Restaurante;
import com.example.demo.domain.repository.CozinhaRespository;
import com.example.demo.domain.repository.RestauranteRepository;

@Service
public class CadastroRestauranteService {
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CozinhaRespository cozinhaRespository;
	
	public Restaurante salvar(Restaurante restaurante) {
		Long id = restaurante.getCozinha().getId();
		Cozinha cozinha = cozinhaRespository.buscar(id);
		if (cozinha == null) {
			throw new EntidadeNaoEncontradaException(String.format("Cozinha com codigo %d não encontrada", id));
		}
		restaurante.getCozinha().setNome(cozinha.getNome());
		return restauranteRepository.salvar(restaurante);
	}
	
	public void remover(Long id) {
		try {
			restauranteRepository.remover(id);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.format("Restaurante %d não encontrado", id));
		}
	}
	
	
	
}
