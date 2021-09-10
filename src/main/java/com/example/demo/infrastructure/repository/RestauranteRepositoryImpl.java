package com.example.demo.infrastructure.repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.example.demo.domain.model.Restaurante;
import com.example.demo.respository.queries.RestauranteRespositoryQueries;

//A VANTAGEM É QUE QUANDO SE COLOCA A CONSULTA AQUI, ELA FICA EM CODIGO JAVA E COM ISSO SE PODE FAZER VARIAS COISAS.

@Repository
public class RestauranteRepositoryImpl implements RestauranteRespositoryQueries {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal){
		var jpql = new StringBuilder();
		jpql.append("from Restaurante where 0 = 0 ");
		
		var parametros = new HashMap<String, Object>();
		
		if (StringUtils.hasLength(nome)) {
			jpql.append("and nome like:nome ");
			parametros.put("nome", "%"+nome+"%");
		}
		
		if(taxaFreteInicial != null) {
			jpql.append("and taxaFrete >= :taxaFreteInicial ");
			parametros.put("taxaFreteInicial", taxaFreteInicial);
		}
		
		if (taxaFreteFinal != null) {
			jpql.append("and taxaFrete <= :taxaFreteFinal");
			parametros.put("taxaFreteFinal", taxaFreteFinal);
		}
		
		//to string para converter para string pois o createquey recebe um string e não uma StringBuilder		
		TypedQuery<Restaurante> query = manager.createQuery(jpql.toString(), Restaurante.class);
				
		parametros.forEach((chave, valor)->{
			query.setParameter(chave, valor);
		});
		
		return query.getResultList();
	}
}
