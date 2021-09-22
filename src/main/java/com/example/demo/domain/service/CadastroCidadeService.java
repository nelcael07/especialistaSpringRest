package com.example.demo.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.domain.exception.CidadeNaoEncontradoException;
import com.example.demo.domain.exception.EntidadeEmUsoException;
import com.example.demo.domain.model.Cidade;
import com.example.demo.domain.model.Estado;
import com.example.demo.domain.repository.CidadeRepository;

@Service
public class CadastroCidadeService {
	
	private static final String MSG_CIDADE_EM_USO = "A cidade %d está sendo usada";

	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private CadastroEstadoService cadastroEstado;
	
	public void remover(Long id) {
		try {
			cidadeRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new CidadeNaoEncontradoException(id);
		} catch(DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_CIDADE_EM_USO, id));
		}
	}
	
	public Cidade salvar(Cidade cidade) {
		Long id = cidade.getEstado().getId();
		Estado estado = cadastroEstado.buscar(id);
		cidade.setEstado(estado);
		return cidadeRepository.save(cidade);
	}
	
	public Cidade buscar(Long id) {
		return cidadeRepository.findById(id).orElseThrow(
				()-> new CidadeNaoEncontradoException(id));
	}
	
}
