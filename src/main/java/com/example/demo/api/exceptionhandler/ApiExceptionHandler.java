package com.example.demo.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.demo.domain.exception.EntidadeEmUsoException;
import com.example.demo.domain.exception.EntidadeNaoEncontradaException;
import com.example.demo.domain.exception.NegocioException;
import com.fasterxml.jackson.databind.exc.IgnoredPropertyException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{

	private static final String SISTEMA_ERROR = "Ocorreu um erro interno inesperado no sistema. Tente novamente e se o"
			+ "problema persistir, entre em contato com o administrador do sistema";
	
	ProblemType problemTypeRecursoNaoEncontrada = ProblemType.RECURSO_NAO_ENCONTRADA;
	ProblemType problemTypeEntidadeInternaNaoEncontrada = ProblemType.ENTIDADE_INTERNA_NAO_ENCONTRADA;
	ProblemType problemTypeEntidadeEmUso = ProblemType.ENTIDADE_EM_USO;
	ProblemType problemTypeMensagemInconpreensivel = ProblemType.MENSAGEM_INCOMPREENSIVEL;
	ProblemType problemTypeEntidadeIgnorada = ProblemType.ENTIDADE_IGNORADA;
	ProblemType problemTypeEntidadeNaoExiste = ProblemType.ENTIDADE_NAO_EXISTE;
	ProblemType problemTypeParamentroInvalido = ProblemType.PARAMETRO_INVALIDO;
	ProblemType problemTypeErroInterno = ProblemType.ERRO_NO_SISTEMA;
	ProblemType problemTypeParametroViolado = ProblemType.PARAMENTRO_VIOLADO;
	
	
	//trata a handleExceptionInternal	
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		if (body == null) {
			body = Problem.builder()
					.title(status.getReasonPhrase())
					.status(status.value())
					.timestamp(LocalDateTime.now())
					.build();
		} 
		
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String details = "Um ou mais campos estão invalidos. Faça o preenchimento correto e tente novamente.";
		
		//bindingresult contem as contraints violadas		
		BindingResult binding = ex.getBindingResult();
		
		List<Problem.Field> fields = binding.getFieldErrors().stream()
				.map(fieldError -> Problem.Field.builder()
						.name(fieldError.getField())
						.userMessage(fieldError.getDefaultMessage())
						.build()
						)
				.collect(Collectors.toList() );
		
		
		Problem problem = createdProblem(
				status,
				problemTypeParametroViolado,
				details)
				.timestamp(LocalDateTime.now())
				.fields(fields)
				.userMessage(details)
				.build(); 
				
		return handleExceptionInternal(ex, problem, headers, status, request);
	}
	
	
	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<?> handleEstadoNaoEncontradoException(EntidadeNaoEncontradaException e, WebRequest request){
		Problem problem = createdProblem(HttpStatus.NOT_FOUND,
				problemTypeRecursoNaoEncontrada,
				e.getMessage())
				.userMessage(e.getMessage())
				.timestamp(LocalDateTime.now())
				.build();
		return handleExceptionInternal(e, problem, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<?> handleNegocioException(NegocioException e,  WebRequest request ){
		Problem problem =  createdProblem(HttpStatus.BAD_REQUEST,
				problemTypeEntidadeInternaNaoEncontrada, 
				e.getMessage())
				.userMessage(e.getMessage())
				.timestamp(LocalDateTime.now())
				.build();
		return handleExceptionInternal(e, problem, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler(EntidadeEmUsoException.class)
	public ResponseEntity<?> handleEntidadeEmUsoException(EntidadeEmUsoException e,  WebRequest request){
		Problem problem =  createdProblem(HttpStatus.CONFLICT,
				problemTypeEntidadeEmUso, 
				e.getMessage())
				.userMessage(e.getMessage())
				.timestamp(LocalDateTime.now())
				.build();
		return handleExceptionInternal(e, problem, new HttpHeaders(), HttpStatus.CONFLICT, request);
	}
	
	
	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
		if(ex instanceof MethodArgumentTypeMismatchException) {
			return handleMethodArgumentTypeMismatchException((MethodArgumentTypeMismatchException)ex, headers, status, request);
		}
		
		return super.handleTypeMismatch(ex, headers, status, request);
	}
	
	private ResponseEntity<Object> handleMethodArgumentTypeMismatchException (MethodArgumentTypeMismatchException e ,
			HttpHeaders headers,
			HttpStatus status,
			WebRequest request){
		
		String details = 
				String.format("O parâmentro de url '%s' recebeu o valor '%s' que é do tipo invalido."
						+ " Corrija e informe um valor compativel com o tipo %s"
				, e.getName(), e.getValue(), e.getRequiredType().getSimpleName());
			
		Problem problem = createdProblem(status, 
				problemTypeParamentroInvalido ,
				details
				)
				.userMessage(SISTEMA_ERROR)
				.timestamp(LocalDateTime.now())
				.build();
		
		return handleExceptionInternal(e, problem, headers, status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
		String details = String.format("O recurso %s, que vovê tentou acessar, é inexistente",
				ex.getRequestURL());
		
		Problem problem = createdProblem(
				status,
				problemTypeRecursoNaoEncontrada,
				details
				)
				.userMessage(SISTEMA_ERROR)
				.timestamp(LocalDateTime.now())
				.build();
		
		return handleExceptionInternal(ex, problem, headers, status, request);
	}
	
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException e,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Throwable rootCause = ExceptionUtils.getRootCause(e);
		
		if (rootCause instanceof InvalidFormatException) {
			return handleInvalidFormatException((InvalidFormatException) rootCause, headers, status, request);
		}else  if(rootCause instanceof IgnoredPropertyException) {
			return handleIgnoredPropertyException((IgnoredPropertyException) rootCause, headers, status, request);
		}  else if (rootCause instanceof UnrecognizedPropertyException) {
			return handleUnrecognizedPropertyException((UnrecognizedPropertyException) rootCause, headers, status, request);
		}
		
		Problem problem = createdProblem(status,
				problemTypeMensagemInconpreensivel,
				"O corpo da requisição está invalido. Verifique erro de sintaxe.")
				.userMessage(SISTEMA_ERROR)
				.timestamp(LocalDateTime.now())
				.build();
		
		return super.handleExceptionInternal(e, problem, new HttpHeaders(), status, request);
	}
	
	private ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException e ,
			HttpHeaders headers, HttpStatus status, WebRequest request){
		String path = e.getPath().stream()
				.map(ref -> ref.getFieldName())
				.collect(Collectors.joining("."));
		
		String details = String.format("A propriedade '%s' recebeu o valor '%s',"
				+ "que é de um tipo invalido. Corrija e informe um valor compativel com o tipo '%s'", 
				path, e.getValue(), e.getTargetType().getSimpleName());
		
		
		Problem problem = createdProblem(
				status,
				problemTypeMensagemInconpreensivel,
				details
				)
				//não se coloca essa propriedade no createdProblem pq nem todos as capturas terão que ter esse elemento.				
				.userMessage(SISTEMA_ERROR)
				.timestamp(LocalDateTime.now())
				.build();
		
		return handleExceptionInternal(e, problem, headers, status, request);
	}
	
	
	private ResponseEntity<Object> handleIgnoredPropertyException(IgnoredPropertyException e , HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String path = e.getPath().stream().map(ref -> ref.getFieldName()).collect(Collectors.joining("."));
		
		String details = String.format("A propriedade %s está sendo ignorada na representação", path);
		
		Problem problem = createdProblem(
				status,
				problemTypeEntidadeIgnorada,
				details
				)
				.userMessage(SISTEMA_ERROR)
				.timestamp(LocalDateTime.now())
				.build();
		
		return handleExceptionInternal(e, problem, headers, status, request);
	};
	
	private ResponseEntity<Object> handleUnrecognizedPropertyException(UnrecognizedPropertyException e ,
			HttpHeaders headers,
			HttpStatus status,
			WebRequest request){
		String path = e.getPath()
				.stream()
				.map(ref -> ref.getFieldName())
				.collect(Collectors.joining("."));
		String details = String.format("A propriedade %s não existe na representação.", path);
		Problem problem = createdProblem(
				status,
				problemTypeEntidadeNaoExiste,
				details
				)
				.userMessage(SISTEMA_ERROR)
				.timestamp(LocalDateTime.now())
				.build();
		
		return handleExceptionInternal(e, problem, headers, status, request);
	}
	
	
	//vai pegar todas exceções não tratadas	nas demais funções
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleAll(Exception e , WebRequest resquest){
		String details = SISTEMA_ERROR;
		
		Problem problem = createdProblem(
				HttpStatus.INTERNAL_SERVER_ERROR,
				problemTypeErroInterno,
				details)
				.userMessage(SISTEMA_ERROR)
				.timestamp(LocalDateTime.now())
				.build();
		return handleExceptionInternal(e, problem, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, resquest);
	}
	
	
	
	//metodo para criar uma instancia de Problem	
	private Problem.ProblemBuilder createdProblem (HttpStatus status, ProblemType problemType, String details) {
		return Problem.builder()
				.status(status.value())
				.type(problemType.getUri())
				.title(problemType.getTitle())
				.details(details);
	}
	
}
