package com.example.demo.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.model.Cozinha;

@Component
public class CadastroCozinha {
	
	
	//entity manager faz a gerencia dos dados persistidos	
	@PersistenceContext
	private EntityManager manager;
	
	public List<Cozinha> listar(){
		//o metodo createQuery retorna um typedquery do tipo cozinha		
		return  manager.createQuery("from Cozinha", Cozinha.class ).getResultList();
	}
	
	// quando vai adicionar algo no banco de dados, tem que usar a anotação transition	
	@Transactional
	public Cozinha adicionar(Cozinha cozinha) {
		//merge significa fundir,juntar		
		return manager.merge(cozinha);
	}
	
	//Busca por id			
	public Cozinha buscar(Long id) {
		return manager.find(Cozinha.class, id);
	}
}
