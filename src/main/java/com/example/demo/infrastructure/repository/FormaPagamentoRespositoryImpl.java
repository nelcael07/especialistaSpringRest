package com.example.demo.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.model.Cozinha;
import com.example.demo.domain.model.FormaPagamento;
import com.example.demo.domain.repository.FormaPagamentoRepository;

@Component
public class FormaPagamentoRespositoryImpl implements FormaPagamentoRepository{
	
	@Autowired
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<FormaPagamento> listar(){
		return manager.createQuery("from forma_pagamento", FormaPagamento.class).getResultList();
	}
	
	@Override
	public FormaPagamento buscar(Long id) {
		return manager.find(FormaPagamento.class, id);
	}
	
	@Transactional
	@Override
	public FormaPagamento salvar(FormaPagamento formaPagamento) {
		return manager.merge(formaPagamento);
	}
	
	@Transactional
	@Override
	public void remover(FormaPagamento formaPagamento) {
		FormaPagamento query = buscar(formaPagamento.getId());
		manager.remove(query);
	}
}
