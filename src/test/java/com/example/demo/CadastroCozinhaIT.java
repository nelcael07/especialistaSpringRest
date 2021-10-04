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
	
//	private FlywayAutoConfiguration flyway;
	
	@BeforeEach
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/cozinhas";
		
		//antes de todos os metodos, vai ser executado o afterMigrate para que o banco fique em um estado padrão para os testes.		
		//flyway.migrate();
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
