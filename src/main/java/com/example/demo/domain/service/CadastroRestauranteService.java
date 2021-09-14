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
		// aqui basicamente eu to mandando ele retornar o Optional e verificar com o orelsethrow se vai ter algo de cozinha, se não tiver ele lança a exeção.		
		Cozinha cozinha = cozinhaRespository.findById(id).orElseThrow(
				()-> new EntidadeNaoEncontradaException(String.format("Cozinha com codigo %d não encontrada", id)));
		restaurante.setCozinha(cozinha);
		return restauranteRepository.save(restaurante);
	}
	
	public void remover(Long id) {
		try {
			restauranteRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.format("Restaurante %d não encontrado", id));
		}
	}
	
	
	
}
