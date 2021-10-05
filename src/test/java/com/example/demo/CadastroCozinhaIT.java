package com.example.demo;

import static org.hamcrest.CoreMatchers.equalTo;
import org.hamcrest.Matchers;
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
	
	private static final int ID_COZINHA_INEXISTENTE = 100;

	@LocalServerPort
	private int port;
	
	@Autowired
	private DatabaseCleaner dataBase;
	
	@Autowired
	private CozinhaRespository cozinhaRepository;
	
	@Autowired
	private Leitura leitura;
	
	private String jsonCorretoCozinhaChinesa;
	
	private int numerodeCozinhas;
	
	private Cozinha cozinhaBrasileria;

	@BeforeEach
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/cozinhas";
		dataBase.clearTables();
		prepararDados();
		
		jsonCorretoCozinhaChinesa = leitura.getContentFromResource(
				"/json/Cozinha.json");
	}
	
	private void prepararDados() {
		
		Cozinha cozinha1 = new Cozinha();
		cozinha1.setNome("Tailandesa");
		cozinhaRepository.save(cozinha1); 
		
		cozinhaBrasileria = new Cozinha(); 
		cozinhaBrasileria.setNome("Brasileira");
		cozinhaRepository.save(cozinhaBrasileria);
		
		Cozinha cozinha3 = new Cozinha(); 
		cozinha3.setNome("Argentina");
		cozinhaRepository.save(cozinha3);
		
		numerodeCozinhas = cozinhaRepository.findAll().size();
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
			.body("nome", Matchers.hasSize(numerodeCozinhas))
			.body("nome", Matchers.hasItems("Argentina", "Brasileira"));
	}
	
	@Test
	public void testarDeveRetornarStatus201_QuandoCadastrarCozinha() {
		RestAssured.given()
			.body(jsonCorretoCozinhaChinesa)
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
			.pathParam("cozinhaId", cozinhaBrasileria.getId())
			.accept(ContentType.JSON)
		.when()
			.get("/{cozinhaId}")
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("nome", equalTo(cozinhaBrasileria.getNome()));
	}
	
	@Test
	public void testarErroBuscaUnitaria() {
		RestAssured.given()
			.pathParam("cozinhaId", ID_COZINHA_INEXISTENTE)
			.accept(ContentType.JSON)
		.when()
			.get("/{cozinhaId}")
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());

	}
	
}
	