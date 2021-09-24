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
	
}
