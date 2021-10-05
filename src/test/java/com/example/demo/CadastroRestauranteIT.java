package com.example.demo;

import static org.hamcrest.CoreMatchers.equalTo;

import java.math.BigDecimal;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import com.example.demo.domain.model.Cozinha;
import com.example.demo.domain.model.Restaurante;
import com.example.demo.domain.repository.RestauranteRepository;
import com.example.demo.util.DatabaseCleaner;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
class CadastroRestauranteIT {
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private DatabaseCleaner dataBase;
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private Leitura leitura;
	
	private Restaurante restaurante;
	
	private int numerodeRestaurantes;
	
	private String jsonRestaurantes;
	
	private String jsonRestaurantesAtualizado;
	
	@BeforeEach
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/restaurantes";
		dataBase.clearTables();
		prepararDados();
		
		jsonRestaurantes = leitura.getContentFromResource(
				"/json/Restaurante.json");
		
		jsonRestaurantesAtualizado = leitura.getContentFromResource(
				"/json/RestauranteAtualizacao.json");
	}
	
	private void prepararDados() {
		Restaurante restaurante1 = new Restaurante();
		restaurante1.setNome("restaurante de teste");
		restaurante1.setTaxaFrete(new BigDecimal(20));
		Cozinha cozinha = new Cozinha();
		cozinha.setId(1L);
		restaurante1.setCozinha(cozinha);
		restauranteRepository.save(restaurante1);
		
		restaurante = new Restaurante();
		restaurante.setNome("restaurante de teste");
		restaurante.setTaxaFrete(new BigDecimal(20));
		Cozinha cozinha1 = new Cozinha();
		cozinha1.setId(1L);
		restaurante.setCozinha(cozinha1);
		restauranteRepository.save(restaurante);
		
		numerodeRestaurantes = restauranteRepository.findAll().size();
		System.out.println("passou");
	}
	
	//get	
	@Test
	public void testarListarRestaurantes() {
		RestAssured.given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.body("taxaFrete", Matchers.hasSize(numerodeRestaurantes))
			.statusCode(HttpStatus.OK.value());
	}
	
	//get/id	
	@Test
	public void testarBuscarRestaurante() {
		RestAssured.given()
			.accept(ContentType.JSON)
			.pathParam("restauranteId", restaurante.getId())
		.when()
			.body("/{restauranteId}")
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("nome", equalTo(restaurante.getNome()));
	}
	
	//post	
	@Test
	public void testarAdicionarRestaurante() {
		RestAssured.given()
			.body(jsonRestaurantes)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());
	}
	
	//put	
	@Test
	public void testarAtualização() {
		RestAssured.given()
			.body(jsonRestaurantesAtualizado)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("restauranteId", restaurante.getId())
		.when()
			.put("/{restauranteId}")
		.then()
			.statusCode(HttpStatus.NO_CONTENT.value())
			.body("nome", equalTo(restaurante.getNome()));
	}
	
}
