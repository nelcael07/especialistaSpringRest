package com.example.demo.infrastructure.repository;

import static com.example.demo.infrastructure.repository.spec.RestauranteSpecs.comFreteGratis;
import static com.example.demo.infrastructure.repository.spec.RestauranteSpecs.comNomeSemelhante;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import com.example.demo.domain.model.Restaurante;
import com.example.demo.domain.repository.RestauranteRepository;
import com.example.demo.infrastructure.respository.queries.RestauranteRespositoryQueries;


@Repository
public class RestauranteRepositoryImpl implements RestauranteRespositoryQueries {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired 
	@Lazy
	private RestauranteRepository restauranteRepository;

	@Override
	public List<Restaurante> findComFreteGratis(String nome){
		return restauranteRepository.findAll(comFreteGratis().and(comNomeSemelhante(nome)));
	}
	
	
//	@Override
//	public List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal){
//		CriteriaBuilder builder = manager.getCriteriaBuilder();
//		
//		CriteriaQuery<Restaurante> criteria = builder.createQuery(Restaurante.class);
//		
//		Root<Restaurante> root = criteria.from(Restaurante.class);
//		
//		var predicates = new ArrayList<Predicate>();
//		
////		if (StringUtils.hasText(nome)) {
////			predicates.add( builder.like(root.get("nome"), "%"+ nome +"%"));
////		}
////		
////		if (taxaFreteInicial!=null) {
////			predicates.add(builder.greaterThanOrEqualTo(root.get("taxaFrete"), taxaFreteInicial));
////		}
////		
////		if (taxaFreteFinal != null) {
////			predicates.add(builder.lessThanOrEqualTo(root.get("taxaFrete"), taxaFreteFinal));
////		}
////		
////		criteria.where(predicates.toArray(new Predicate[0]));  
//		
//		TypedQuery<Restaurante> query = manager.createQuery(criteria);
//		 
//		return query.getResultList();
//	}
	
	
	
}
