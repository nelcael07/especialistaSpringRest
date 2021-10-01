package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

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
		RestAssured.given()
			.basePath("/cozinhas")
			.port(port)
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(200);
		
	}
	
}
