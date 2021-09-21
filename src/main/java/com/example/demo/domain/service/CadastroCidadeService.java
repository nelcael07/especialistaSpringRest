package com.example.demo.domain.service;

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
	
	private static final String MSG_CIDADE_NAO_ENCONTRADA = "Cidade %d não encontrada.";

	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private CadastroEstadoService cadastroEstado;
	
	public void remover(Long id) {
		try {
			cidadeRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.format(MSG_CIDADE_NAO_ENCONTRADA, id));
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
				()-> new EntidadeNaoEncontradaException(String.format(MSG_CIDADE_NAO_ENCONTRADA, id)));
	}
	
}
