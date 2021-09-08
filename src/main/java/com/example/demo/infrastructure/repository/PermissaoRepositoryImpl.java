package com.example.demo.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.model.Permissao;
import com.example.demo.domain.repository.PermissaoRespository;


@Repository
public class PermissaoRepositoryImpl implements PermissaoRespository {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Permissao> listar(){
		return manager.createQuery("from Permissao", Permissao.class).getResultList();
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
		permissao = buscar(permissao.getId());
		manager.remove(permissao);
	}
	
	
	
	
	
	
	
	
	
}
