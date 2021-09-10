package com.example.demo.domain.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.model.Restaurante;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long>{
	
	List<Restaurante> getByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);
	
	List<Restaurante> findByNomeContainingAndCozinhaId(String nome, Long id);
	
	// pegando o primeiro do filtro de nome.	
	Optional<Restaurante> getFirstByNomeContaining(String nome);
	
	List<Restaurante> getTop2ByNomeContaining(String nome);
	
	int countByCozinhaId(Long id);
}