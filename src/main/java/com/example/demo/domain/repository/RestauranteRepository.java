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

//implementar JpaspecificationExecutor faz com que possa ser utilzado spec nessa classe.
@Primary
public interface RestauranteRepository extends CustomJpaRepository<Restaurante, Long>, RestauranteRespositoryQueries, 
				JpaSpecificationExecutor<Restaurante>{
	
	//fazendo join de cozinha junto com a consulta de restaurante, ou seja, ele faz uma consulta só.
	//o left join fetch faz o join funcionar com o final tomany.
	//isso ocorre por que no tomany o padrão é lazy.
	
	//O PROBLEMA DESSA CONSULTA É QUE ELA GERA MAIS RESTAURANTES NO JSON.
	@Query("from Restaurante r join fetch r.cozinha left join fetch r.formasPagamento")
	List<Restaurante> findAll();
	
	List<Restaurante> getByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);

//	List<Restaurante> consultarPorNome(String nome, Long id);

	Optional<Restaurante> getFirstByNomeContaining(String nome);

	List<Restaurante> getTop2ByNomeContaining(String nome);

	int countByCozinhaId(Long id);

}