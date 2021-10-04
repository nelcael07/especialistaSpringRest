package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//mandando ele usar o application.properties especificado quando for rodar os testes.
//@TestPropertySource("/application-test.properties")
class CadastroCozinhaIT {
	
	@LocalServerPort
	private int port;

	@BeforeEach
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
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

//	@Test
//	public void testarRetornarTodasCozinhas(){
//		RestAssured.given()
//			.accept(ContentType.JSON)
//		.when()
//			.get()
//		.then()
//			.body("nome", Matchers.hasSize(11))
//			.body("nome", Matchers.hasItems("Italiana", "Brasileira"));
//	}
	
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
