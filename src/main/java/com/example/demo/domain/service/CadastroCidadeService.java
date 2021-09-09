package com.example.demo.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.domain.exception.EntidadeNaoEncontradaException;
import com.example.demo.domain.model.Cidade;
import com.example.demo.domain.model.Estado;
import com.example.demo.domain.repository.CidadeRepository;
import com.example.demo.domain.repository.EstadoRepository;

@Service
public class CadastroCidadeService {
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	public void remover(Long id) {
		try {
			cidadeRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.format("Não existe cidade com o id %d", id));
		}
	}
	
	public Cidade salvar(Cidade cidade) {
		Long id = cidade.getEstado().getId();
		Estado estado = estadoRepository.findById(id).orElseThrow(() ->
			new EntidadeNaoEncontradaException(String.format("Estado com id %d não encontrada", id)));
		cidade.setEstado(estado);
		return cidadeRepository.save(cidade);
	}
}
