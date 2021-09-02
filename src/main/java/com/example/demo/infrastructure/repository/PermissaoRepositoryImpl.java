package com.example.demo.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import com.example.demo.domain.model.Permissao;
import com.example.demo.domain.repository.PermissaoRespository;


@Component
public class PermissaoRepositoryImpl implements PermissaoRespository {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Permissao> listar(){
		return manager.createQuery("from permissao", Permissao.class).getResultList();
	}
	
	@Override
	public Permissao salvar(Permissao permissao) {
		return manager.merge(permissao);
	}
	
	@Override
	public Permissao buscar(Long id) {
		return manager.find(Permissao.class, id);
	}
	
	@Override
	public void remover(Permissao permissao) {
		Permissao query = buscar(permissao.getId());
		manager.remove(query);
	}
	
	
	
	
	
	
	
	
	
}
