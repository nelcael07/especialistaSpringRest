package com.example.demo.infrastructure.repository;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.stereotype.Repository;

import com.example.demo.domain.model.Restaurante;
import com.example.demo.respository.queries.RestauranteRespositoryQueries;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRespositoryQueries {
	
	@PersistenceContext
	private EntityManager manager;
	
	
	@Override
	public List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal){
				//criteriabuilder é a construtura das querys, eu uso para dizer qual query quero criar.		
				CriteriaBuilder builder = manager.getCriteriaBuilder();
				
				//o criteriaquery é os criterios da query.		
				CriteriaQuery<Restaurante> criteria = builder.createQuery(Restaurante.class);
				
				//adicionando criterios a query		
				criteria.from(Restaurante.class); // from restaurante
				
				//o createQuery retorna sempre uma typedQuery.		
				TypedQuery<Restaurante> query = manager.createQuery(criteria);
				 
				return manager.createQuery("from Restaurante",Restaurante.class).getResultList();
				
	}
}
