package com.example.demo.api.assembler;

import org.springframework.stereotype.Component;

import com.example.demo.api.model.input.RestauranteInput;
import com.example.demo.domain.model.Cozinha;
import com.example.demo.domain.model.Restaurante;

@Component
public class RestauranteModelDiassembler {
	
	public Restaurante toDomainObject(RestauranteInput restauranteInput) {
		Restaurante restaurante = new Restaurante();
		
		restaurante.setNome(restauranteInput.getNome());
		restaurante.setTaxaFrete(restauranteInput.getTaxaFrete());
		Cozinha cozinha = new Cozinha();
		cozinha.setId(restauranteInput.getCozinha().getId());
		cozinha.setNome(restauranteInput.getNome());
		restaurante.setCozinha(cozinha);
		
		return restaurante;
	}
	
}
