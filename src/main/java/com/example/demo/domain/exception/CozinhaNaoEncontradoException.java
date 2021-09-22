package com.example.demo.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class CozinhaNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;
	
	public CozinhaNaoEncontradoException (String mensagem) {
		super(mensagem);
	}
	
	public CozinhaNaoEncontradoException(Long id) {
		this(String.format("Cozinha %d não encontrado", id));
	}
	
}
