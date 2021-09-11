package com.example.demo.domain.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.demo.domain.model.Restaurante;
import com.example.demo.infrastructure.respository.queries.RestauranteRespositoryQueries;

//implementar JpaspecificationExecutor faz com que possa ser utilzado spec nessa classe.
@Primary
public interface RestauranteRepository extends JpaRepository<Restaurante, Long>, RestauranteRespositoryQueries, 
				JpaSpecificationExecutor<Restaurante>{
	
	List<Restaurante> getByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);

//	List<Restaurante> consultarPorNome(String nome, Long id);

	Optional<Restaurante> getFirstByNomeContaining(String nome);

	List<Restaurante> getTop2ByNomeContaining(String nome);

	int countByCozinhaId(Long id);

}