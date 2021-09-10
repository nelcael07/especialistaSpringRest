package com.example.demo.infrastructure.repository;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.example.demo.domain.model.Restaurante;
import com.example.demo.domain.repository.RestauranteRespositoryQueries;

//A VANTAGEM É QUE QUANDO SE COLOCA A CONSULTA AQUI, ELA FICA EM CODIGO JAVA E COM ISSO SE PODE FAZER VARIAS COISAS.

@Repository
public class RestauranteRepositoryImpl implements RestauranteRespositoryQueries {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal){
		var jpql = "from Restaurante where nome like :nome "
				+ "and taxaFrete between :taxaFreteInicial and :taxaFreteFinal";
		return manager.createQuery(jpql, Restaurante.class).setParameter("nome", "%"+nome+"%" )
				.setParameter("taxaFreteInicial", taxaFreteInicial)
				.setParameter("taxaFreteFinal", taxaFreteFinal)
				.getResultList();
	}
}
