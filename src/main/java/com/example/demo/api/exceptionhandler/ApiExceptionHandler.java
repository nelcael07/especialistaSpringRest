package com.example.demo.api.exceptionhandler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.demo.domain.exception.EntidadeEmUsoException;
import com.example.demo.domain.exception.EntidadeNaoEncontradaException;
import com.example.demo.domain.exception.NegocioException;

//anotação diz que essa classe vai ser a que vai captar as exceções de todos os controllers para retornar EntityManager aqui.
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{
  
	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<?> handleEstadoNaoEncontradoException(EntidadeNaoEncontradaException e){
		Problema problema = Problema.builder()
				.dataHora(LocalDateTime.now())
				.Mensagem(e.getMessage())
				.build();
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(problema);
	}
	
	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<?> handleNegocioException(NegocioException e ){
 		Problema problema = Problema.builder()
				.dataHora(LocalDateTime.now())
				.Mensagem(e.getMessage())
				.build();
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problema);
	}
	

	@ExceptionHandler(EntidadeEmUsoException.class)
	public ResponseEntity<?> handleEntidadeEmUsoException(EntidadeEmUsoException e){
		Problema problema = Problema.builder()
				.dataHora(LocalDateTime.now())
				.Mensagem(e.getMessage())
				.build();
		return ResponseEntity.status(HttpStatus.CONFLICT).body(problema);
	}
	
	
}
