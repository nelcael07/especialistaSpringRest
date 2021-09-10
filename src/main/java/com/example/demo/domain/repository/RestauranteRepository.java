package com.example.demo.domain.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.model.Restaurante;
import com.example.demo.respository.queries.RestauranteRespositoryQueries;

//IMPLEMENTA RESTAURANTERESPOSITORYQUERIES PARA TER O METODO FIND
public interface RestauranteRepository extends JpaRepository<Restaurante, Long>, RestauranteRespositoryQueries{
	
	List<Restaurante> getByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);
	
	List<Restaurante> consultarPorNome(String nome, Long id);
	
	Optional<Restaurante> getFirstByNomeContaining(String nome);
	
	List<Restaurante> getTop2ByNomeContaining(String nome);
	
	int countByCozinhaId(Long id);
	
}