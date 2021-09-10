package com.example.demo.domain.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.domain.model.Restaurante;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long>{
	
	List<Restaurante> getByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);
	
	//query é onde se monta a consulta para busca no banco de dados,essa é uma boa pratica para não ficar tão grande o nome do metodo.
	//JPQL	
	@Query("from Restaurante where nome like %:nome% and cozinha.id = :id")
	List<Restaurante> consultarPorNome(String nome, Long id);
	
//	List<Restaurante> findByNomeContainingAndCozinhaId(String nome, Long id);
	
	// pegando o primeiro do filtro de nome.	
	Optional<Restaurante> getFirstByNomeContaining(String nome);
	
	List<Restaurante> getTop2ByNomeContaining(String nome);
	
	int countByCozinhaId(Long id);
}