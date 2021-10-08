package com.example.demo.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.domain.exception.CozinhaNaoEncontradoException;
import com.example.demo.domain.exception.EntidadeEmUsoException;
import com.example.demo.domain.model.Cozinha;
import com.example.demo.domain.repository.CozinhaRespository;

//CLASS DE DOMAIN NÃO TEM CONTATO COM STATUS HTTPS.

@Service 
public class CadastroCozinhaService {
	
	private static final String MSG_COZINHA_EM_USO = "Cozinha de codigo %d não pode ser removida, ela está sendo usada";

	@Autowired
	private CozinhaRespository cozinhaRepository;
	
	@Transactional
	public Cozinha salvar(Cozinha cozinha) {
		return cozinhaRepository.save(cozinha);
	}
	
	@Transactional
	public void remover(Long id) {
		try {
			cozinhaRepository.deleteById(id);
		}catch (EmptyResultDataAccessException e) {
			throw new CozinhaNaoEncontradoException(id);
		} 
		catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_COZINHA_EM_USO , id));
		}
	}
 	
	public Cozinha buscar(Long id) {
		return cozinhaRepository.findById(id)
				.orElseThrow(()-> new CozinhaNaoEncontradoException(id));
	}
	
	
}
