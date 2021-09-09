package com.example.demo.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import com.example.demo.domain.exception.EntidadeEmUsoException;
import com.example.demo.domain.exception.EntidadeNaoEncontradaException;
import com.example.demo.domain.model.Cozinha;
import com.example.demo.domain.repository.CozinhaRespository;

@Service
public class CadastroCozinhaService {
	
	@Autowired
	private CozinhaRespository cozinhaRepository;
	
	public Cozinha salvar(Cozinha cozinha) {
		return cozinhaRepository.save(cozinha);
	}
	
	public void excluir(Long id) {
		try {
			cozinhaRepository.deleteById(id);;
		}catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de cozinha com o codigo: %d", id));
		}
		catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format("Cozinha de codigo %d não pode ser removida, pois está em uso", id));
		}
	}
	
	
}
