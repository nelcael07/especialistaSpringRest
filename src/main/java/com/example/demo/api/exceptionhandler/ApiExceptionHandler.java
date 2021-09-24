package com.example.demo.api.exceptionhandler;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.demo.domain.exception.EntidadeEmUsoException;
import com.example.demo.domain.exception.EntidadeNaoEncontradaException;
import com.example.demo.domain.exception.NegocioException;

//anotação diz que essa classe vai ser a que vai captar as exceções de todos os controllers para retornar EntityManager aqui.
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{

	ProblemType problemTypeEntidadeNaoEncontrada = ProblemType.ENTIDADE_NAO_ENCONTRADA;
	ProblemType problemTypeEntidadeInternaNaoEncontrada = ProblemType.ENTIDADE_INTERNA_NAO_ENCONTRADA;
	ProblemType problemTypeEntidadeEmUso = ProblemType.ENTIDADE_EM_USO;
	ProblemType problemTypeMensagemInconpreensivel = ProblemType.MENSAGEM_INCOMPREENSIVEL;
	
	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<?> handleEstadoNaoEncontradoException(EntidadeNaoEncontradaException e, WebRequest request){
		Problem problem = createdProblem(HttpStatus.NOT_FOUND,
				problemTypeEntidadeNaoEncontrada,
				e.getMessage()).build();
		return handleExceptionInternal(e, problem, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<?> handleNegocioException(NegocioException e,  WebRequest request ){
		Problem problem =  createdProblem(HttpStatus.BAD_REQUEST,
				problemTypeEntidadeInternaNaoEncontrada, 
				e.getMessage()).build();
		return handleExceptionInternal(e, problem, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler(EntidadeEmUsoException.class)
	public ResponseEntity<?> handleEntidadeEmUsoException(EntidadeEmUsoException e,  WebRequest request){
		Problem problem =  createdProblem(HttpStatus.CONFLICT,
				problemTypeEntidadeEmUso, 
				e.getMessage()).build();
		return handleExceptionInternal(e, problem, new HttpHeaders(), HttpStatus.CONFLICT, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException e,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		Problem problem = createdProblem(status,
				problemTypeMensagemInconpreensivel,
				"O corpo da requisição está invalido. Verifique ero de sintaxe.").build();
		
		return super.handleExceptionInternal(e, problem, new HttpHeaders(), status, request);
		
	}
	
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
		if (body == null) {
			body = Problem.builder()
					.title(status.getReasonPhrase())
					.status(status.value())
					.build();
		} 
		
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}
	
	private Problem.ProblemBuilder createdProblem (HttpStatus status, ProblemType problemType, String details) {
		return Problem.builder()
				.status(status.value())
				.type(problemType.getUri())
				.title(problemType.getTitle())
				.details(details);
	}
	
	
}
