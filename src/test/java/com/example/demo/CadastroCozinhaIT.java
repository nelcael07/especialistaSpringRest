package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import com.example.demo.domain.model.Cozinha;
import com.example.demo.domain.repository.CozinhaRespository;
import com.example.demo.util.DatabaseCleaner;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
class CadastroCozinhaIT {
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private DatabaseCleaner dataBase;
	
	@Autowired
	private CozinhaRespository cozinhaRepository;

	@BeforeEach
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/cozinhas";
		//chamando class que vai limpar as tabelas do banco de dados.		
		dataBase.clearTables();
		prepararDados();
	}
	
	private void prepararDados() {
		
		Cozinha cozinha1 = new Cozinha();
		cozinha1.setNome("Tailandesa");
		cozinhaRepository.save(cozinha1); 
		
		Cozinha cozinha2 = new Cozinha(); 
		cozinha2.setNome("Brasileira");
		cozinhaRepository.save(cozinha2);
		
		Cozinha cozinha3 = new Cozinha(); 
		cozinha3.setNome("Argentina");
		cozinhaRepository.save(cozinha3);
		
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
//			.body("nome", Matchers.hasSize(3))
//			.body("nome", Matchers.hasItems("Italiana", "Brasileira"));
//	}
	
	@Test
	public void testarDeveRetornarStatus201_QuandoCadastrarCozinha() {
		RestAssured.given()
			.body("{ \"nome\" : \"Chinesa\" }")
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then() 
			.statusCode(HttpStatus.CREATED.value());
		
	}
	
	@Test
	public void testarBuscaUnitaria() {
		RestAssured.given()
			.pathParam("cozinhaId", 1)
			.accept(ContentType.JSON)
		.when()
			.get("/{cozinhaId}")
		.then()
			.statusCode(HttpStatus.OK.value());
		
	}
	
	@Test
	public void testarErroBuscaUnitaria() {
		RestAssured.given()
			.pathParam("cozinhaId", 100)
			.accept(ContentType.JSON)
		.when()
			.get("/{cozinhaId}")
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());
		
		
		
		
		
		
	}
		
	
	
}
	