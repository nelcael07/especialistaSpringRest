package com.example.demo.infrastructure.repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import com.example.demo.domain.model.Cidade;
import com.example.demo.domain.repository.CidadeRepository;

@Component
public class CidadeRespositoryImpl implements CidadeRepository{
	
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Cidade> listar() {
		return manager.createQuery("from cidade", Cidade.class).getResultList();
	}
	
	@Override
	public Cidade buscar(Long id) {
		return manager.find(Cidade.class, id);
	}
	
	@Override
	public Cidade salvar(Cidade cidade) {
		return manager.merge(cidade);
	}
	
	@Override
	public void remover(Cidade cidade) {
		Cidade query = buscar(cidade.getId());
		manager.remove(query);
	}
	
}
