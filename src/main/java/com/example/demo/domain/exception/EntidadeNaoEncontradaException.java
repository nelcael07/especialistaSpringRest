package com.example.demo.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
//essa classe só passa a servir para a hierarquia agora com esse abstract.
public abstract class EntidadeNaoEncontradaException extends NegocioException {

	private static final long serialVersionUID = 1L;
	  
	public EntidadeNaoEncontradaException (String mensagem) {
		super(mensagem);
	}
	
}
