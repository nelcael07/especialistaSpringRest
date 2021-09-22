package com.example.demo.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ProdutoNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;
	
	public ProdutoNaoEncontradoException (String mensagem) {
		super(mensagem);
	}
	
	public ProdutoNaoEncontradoException(Long id) {
		this(String.format("Produto %d não encontrado", id));
	}
	
}
