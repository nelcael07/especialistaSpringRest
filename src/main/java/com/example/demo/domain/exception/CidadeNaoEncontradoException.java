package com.example.demo.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class CidadeNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;
	
	public CidadeNaoEncontradoException (String mensagem) {
		super(mensagem);
	}
	
	public CidadeNaoEncontradoException(Long id) {
		this(String.format("Cidade %d não encontrado", id));
	}
	
}
