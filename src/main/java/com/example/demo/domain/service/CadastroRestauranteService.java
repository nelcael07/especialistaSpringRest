package com.example.demo.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.exception.EntidadeNaoEncontradaException;
import com.example.demo.domain.exception.RestauranteNaoEncontradoException;
import com.example.demo.domain.model.Cozinha;
import com.example.demo.domain.model.Restaurante;
import com.example.demo.domain.repository.CozinhaRespository;
import com.example.demo.domain.repository.RestauranteRepository;

@Service
public class CadastroRestauranteService {

	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CadastroCozinhaService cadastroCozinha;
	
	//é uma boa pratica adicionar o transational aqui no metodo publico do service,  	
	@Transactional
	public Restaurante salvar(Restaurante restaurante) {
		Long id = restaurante.getCozinha().getId();
		Cozinha cozinhabuscada = cadastroCozinha.buscar(id);
		restaurante.setCozinha(cozinhabuscada);
		return restauranteRepository.save(restaurante);
	}
	
	  
	public void remover(Long id) {
		try {
			restauranteRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new RestauranteNaoEncontradoException(id);
		}
	}
	
	public Restaurante buscar(Long id) {
		return restauranteRepository.findById(id).orElseThrow(
				() -> new RestauranteNaoEncontradoException(id));
	}
	
	
	
}
