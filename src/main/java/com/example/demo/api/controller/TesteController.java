package com.example.demo.api.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.model.Cozinha;
import com.example.demo.domain.model.Restaurante;
import com.example.demo.domain.repository.CozinhaRespository;
import com.example.demo.domain.repository.RestauranteRepository;

@RestController
@RequestMapping("/teste")
public class TesteController {
	
	@Autowired
	private CozinhaRespository cozinhaRepository;
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@GetMapping("/cozinhas/por-nome")
	public List<Cozinha> nome(@RequestParam String nome){
		return cozinhaRepository.findByNomeContaining(nome);
	}
	
	@GetMapping("/cozinhas/unico-por-nome")
	public Optional<Cozinha> UnicoNome(@RequestParam String nome){
		return cozinhaRepository.findUnicoByNome(nome);
	}
	
	//quando não coloca nada nos atributos o spring acredita que eles serão passados como params da uri.	
	@GetMapping("/restaurantes/entre-inicial-final")
	public List<Restaurante> findByTaxaFreteBetween(BigDecimal inicial, BigDecimal ultima){
		return restauranteRepository.getByTaxaFreteBetween(inicial, ultima);
	}
	
	@GetMapping("/restaurantes/por-nome-cozinhaid")
	public List<Restaurante> findByNomeContainingAndCozinhaId(String nome, Long id){
		return restauranteRepository.findByNomeContainingAndCozinhaId(nome, id);
	}
	
	@GetMapping("/restaurantes/por-nome-primeiro")
	public Optional<Restaurante> firstName(String nome){
		return restauranteRepository.getFirstByNomeContaining(nome);
	}
	
	@GetMapping("/restaurantes/top-dois-primeiros")
	public List<Restaurante> getTop2ByNomeContaining(String nome){
		return restauranteRepository.getTop2ByNomeContaining(nome);
	}
	
	@GetMapping("/cozinhas/verificacao")
	public boolean verificacao(String nome) {
		return cozinhaRepository.existsByNome(nome);
	}
	
	@GetMapping("/restaurantes/contador")
	public int contador(Long id) {
		return restauranteRepository.countByCozinhaId(id);
	}
	
}
