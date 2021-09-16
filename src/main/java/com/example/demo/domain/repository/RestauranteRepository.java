package com.example.demo.domain.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.domain.model.Restaurante;
import com.example.demo.infrastructure.respository.queries.RestauranteRespositoryQueries;

@Primary
public interface RestauranteRepository extends CustomJpaRepository<Restaurante, Long>, RestauranteRespositoryQueries, 
				JpaSpecificationExecutor<Restaurante>{
	
	@Query("from Restaurante r join fetch r.cozinha ")
	List<Restaurante> findAll();
	
	List<Restaurante> getByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);

	Optional<Restaurante> getFirstByNomeContaining(String nome);

	List<Restaurante> getTop2ByNomeContaining(String nome);

	int countByCozinhaId(Long id);
	
}