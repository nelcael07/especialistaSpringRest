package com.example.demo.domain.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.example.demo.domain.model.Restaurante;
import com.example.demo.infrastructure.respository.queries.RestauranteRespositoryQueries;

public interface RestauranteRepository extends CustomJpaRepository<Restaurante, Long>, RestauranteRespositoryQueries, 
				JpaSpecificationExecutor<Restaurante>{
	
	List<Restaurante> getByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);

	Optional<Restaurante> getFirstByNomeContaining(String nome);

	List<Restaurante> getTop2ByNomeContaining(String nome);

	int countByCozinhaId(Long id);
	
}