package com.example.demo.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

//a vantagem de usar o responseStatusException no lugar da @ResposeStatus é que fica flexivel o statuscode ao usar a exception
//@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntidadeNaoEncontradaException extends ResponseStatusException {

	private static final long serialVersionUID = 1L;
	
	public EntidadeNaoEncontradaException (HttpStatus status, String mensagem) {
		//chama o construtor de responseStatusException.		
		super(status, mensagem);
	}
	
	//se essa exception for utilizada com esse construtor, ela terá um statuscode not_found como padrão.	
	public EntidadeNaoEncontradaException (String mensagem) {
		//chama o outro construtor.		
		this(HttpStatus.NOT_FOUND, mensagem);
	}
	
}
