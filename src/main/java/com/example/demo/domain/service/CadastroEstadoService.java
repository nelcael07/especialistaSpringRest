package com.example.demo.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import com.example.demo.domain.exception.EntidadeEmUsoException;
import com.example.demo.domain.exception.EntidadeNaoEncontradaException;
import com.example.demo.domain.model.Estado;
import com.example.demo.domain.repository.EstadoRepository;

@Service
public class CadastroEstadoService {
	
	private static final String ESTADO_NÃO_ENCONTRADO = "Estado %d não encontrado.";
	
	private static final String ESTADO_EM_USO = "Estado %d está sendo usado por alguma cidade";
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	public Estado salvar(Estado estado) {
		return estadoRepository.save(estado);
	}
	
	public void remover(Long id) {
		try {
			estadoRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.format(ESTADO_NÃO_ENCONTRADO, id));
		}catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(ESTADO_EM_USO, id));
		}
	}
	
	public Estado buscar(Long id) {
		return estadoRepository.findById(id).orElseThrow(
				() -> new EntidadeNaoEncontradaException(String.format(ESTADO_NÃO_ENCONTRADO, id)));
	}
	
}
