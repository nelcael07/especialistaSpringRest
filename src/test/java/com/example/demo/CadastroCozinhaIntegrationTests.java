package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

import com.example.demo.domain.exception.CozinhaNaoEncontradoException;
import com.example.demo.domain.exception.EntidadeEmUsoException;
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
	
	@Test
	public void testarFalhaAoCadastrarCozinha() {
		assertThrows(ConstraintViolationException.class, () -> {
			Cozinha cozinha = new Cozinha();
			cozinha.setNome(null);
			cozinha = cadastroCozinha.salvar(cozinha);
		});
	}
	
	@Test
	public void testarFalhaQuandoExcluirCozinhaEmUso() {
		assertThrows(EntidadeEmUsoException.class, () -> {
			cadastroCozinha.remover(1L);
		});
	}
	
	@Test
	public void testarFalhaQuandoExcluirCozinhaInexistente() {
		assertThrows(CozinhaNaoEncontradoException.class, () -> {
			cadastroCozinha.remover(50L);
		});
	}
	
}
