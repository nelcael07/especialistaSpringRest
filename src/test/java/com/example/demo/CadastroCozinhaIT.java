package com.example.demo;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CadastroCozinhaIT {
	
	@LocalServerPort
	private int port;
	
	//metodo de callback, que vai ser executado antes de todos os testes.
	@BeforeEach
	public void setUp() {
		//vai adicionar no console a request e a response quando falhar o test		
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		//porta do RestAssured.		
		RestAssured.port = port;
		//base da url		
		RestAssured.basePath = "/cozinhas";
	}
	
	@Test
	public void testarDeveRetornarStatus200_QuandoConsultarCozinha() {
		RestAssured.given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value());
		
	}

	@Test
	public void testarRetornarTodasCozinhas(){
		RestAssured.given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.bo dy("nome", Matchers.hasSize(11))
			.body("nome", Matchers.hasItems("Italiana", "Brasileira"));
	}
	
	@Test
	public void testarDeveRetornarStatus201_QuandoCadastrarCozinha() {
		RestAssured.given()
			.body("{ \"nome\" : \"Chinesa\" }")
			//está sendo mandado um json.			
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());
		
	}
	
	
	
	
	
}
