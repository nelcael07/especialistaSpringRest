package com.example.demo.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//adicionando o status https na classe da exception para minimizar o codigo do controller.
//reason é a mensagem que será atribuida, mas eu posso adicionar ela no service, que é o aconselhado fazer.
@ResponseStatus(value = HttpStatus.NOT_FOUND)  //, reason = "Entidade não encontrada ")
public class EntidadeNaoEncontradaException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public EntidadeNaoEncontradaException (String mensagem) {
		super(mensagem);
	}
	
}
