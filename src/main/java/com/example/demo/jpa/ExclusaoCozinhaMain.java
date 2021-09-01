package com.example.demo.jpa;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;

import com.example.demo.CursoEspecialistaApplication;
import com.example.demo.domain.model.Cozinha;

public class ExclusaoCozinhaMain {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(CursoEspecialistaApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		CadastroCozinha cadatroCozinha = applicationContext.getBean(CadastroCozinha.class);
		
		Cozinha cozinha = new Cozinha();
		cozinha.setId(1L); 
		cadastroCozinha.remover(cozinha) 
			
	}		
	
}
