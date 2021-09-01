package com.example.demo.jpa;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;

import com.example.demo.CursoEspecialistaApplication;
import com.example.demo.domain.model.Cozinha;


public class ConsultaCozinhaMain {
	
	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(CursoEspecialistaApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);	
		
		CadastroCozinha cadastro Cozinha = applicationContext.getBean(CadastroCozinha.class);
		
		List<Cozinha> cozinhas = cadastroCozinha.listar();
		
		for(Cozinha cozinha: cozinhas) {
			System.out.println(cozinha.getNome());
		}
				
	}
}
