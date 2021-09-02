package com.example.demo.infrastructure.repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import com.example.demo.domain.model.Restaurante;
import com.example.demo.domain.repository.RestauranteRepository;

@Component
public class RestauranteRespositoryImpl implements RestauranteRepository {
	
	@PersistenceContext
	private EntityManager manager;
	
	public List<Restaurante> listar(){
		System.out.println("veio até aqui");
		return manager.createQuery("from restaurante", Restaurante.class).getResultList();
	}
	
	public Restaurante salvar(Restaurante restaurante) {
		return manager.merge(restaurante);
	}
	public Restaurante buscar(Long id) {
		return manager.find(Restaurante.class, id);
	}
	public void remover(Restaurante restaurante) {
		Restaurante query = buscar(restaurante.getId());
		manager.remove(query);
	}
}
