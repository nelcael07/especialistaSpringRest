package com.example.demo;

import static org.hamcrest.CoreMatchers.both;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

//levanta uma aplicação em porta aleatoria para conseguir fazer os teste
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CadastroCozinhaIT {
	
	//pega a porta utilizada para subir a aplicação		
	@LocalServerPort
	private int port; 
	
	@Test
	public void testarDeveRetornarStatus200_QuandoConsultarCozinha() {
		
		//vai adicionar no console a request e a response quando falhar o test		
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		
		//dado que		 
		RestAssured.given()
			//a base do caminho seja /cozinhas			
			.basePath("/cozinhas")
			//na porta			
			.port(port)
			//que aceita json			
			.accept(ContentType.JSON)
		//Quando		
		.when()
			//for feito um metodo get			
			.get()
		//então - validação vai aqui		
		.then()
			//o status code tem que ser 200.				
			.statusCode(HttpStatus.OK.value());
		
	}

	@Test
	public void testarRetornarTodasCozinhas(){
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		
		RestAssured.given()
			.basePath("/cozinhas")
			.port(port)
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			//Matchers uma lib para construir expressões.					
			//validando que eu tenho 4 nomes na response.
			.body("nome", Matchers.hasSize(11))
			//varificando se existe determinados nomes na response.			
			.body("nome", Matchers.hasItems("Italiana", "Brasileira"));
	}
	
	
	
	
	
}
