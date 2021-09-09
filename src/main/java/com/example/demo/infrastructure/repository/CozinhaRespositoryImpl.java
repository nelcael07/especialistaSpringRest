 package com.example.demo.infrastructure.repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.domain.model.Cozinha;
import com.example.demo.domain.repository.CozinhaRespository;

@Repository
public class CozinhaRespositoryImpl implements CozinhaRespository {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Cozinha> listar(){		
		return  manager.createQuery("from Cozinha", Cozinha.class ).getResultList();
	}
	
	@Override
	public List<Cozinha> consultarPorNome(String nome) {
		//ESSE CORINGA O LIKE FAZ ELE BUSCAR COM BASE NO QUE FOI DIGITADO EX: DIGITO A LETRA "A" ELE VAI BUSCAR AS QUE COMEÇA COM LETRA A, PARA USAR ISSO TENHO QUE COLOCAR O CORINGA NO SETPARAMETER.		
		return manager.createQuery("from Cozinha where nome like :nome", Cozinha.class).setParameter("nome", "%"+ nome +"%").getResultList();
	}
	
	@Transactional
	@Override
	public Cozinha salvar(Cozinha cozinha) {
		return manager.merge(cozinha);
	}
	
	@Override
	public Cozinha buscar(Long id) {
		return manager.find(Cozinha.class, id);
	}
	
	@Transactional
	@Override
	public void remover (Long id) {
		Cozinha cozinha = buscar(id);
		if (cozinha == null) {
			//no paramentro de construção eu digo quantos elementos eu esperava, no caso eu esperava pelo menos uma cozinha.			
			throw new EmptyResultDataAccessException(1);
		}
		manager.remove(cozinha);
	}
	
}
