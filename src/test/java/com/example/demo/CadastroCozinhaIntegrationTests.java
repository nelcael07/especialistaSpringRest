package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.lang.reflect.Executable;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.domain.model.Cozinha;
import com.example.demo.domain.service.CadastroCozinhaService;

@SpringBootTest
class CadastroCozinhaIntegrationTests {
	
	@Autowired
	private CadastroCozinhaService cadastroCozinha;
	
	
	@Test
	public void testarCadastroCozinhaComSucesso() {
		//cenario
		Cozinha cozinha  = new Cozinha();
		cozinha.setNome("cozinhaTeste");
		
		//ação
		cozinha = cadastroCozinha.salvar(cozinha);
		
		//validações
		assertThat(cozinha).isNotNull();
		assertThat(cozinha.getId()).isNotNull();
	}
	
	
//	@Test
//	public void deveFalharAoCadastrarCozinha() {
//		Cozinha cozinha = new Cozinha();
//		cozinha = cadastroCozinha.salvar(cozinha);
//		
//		assertThrows(ConstraintViolationException.class,  );
//		
//	}

}
