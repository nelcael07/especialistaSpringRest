 package com.example.demo.jpa;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;

import com.example.demo.CursoEspecialistaApplication;
import com.example.demo.domain.model.Cozinha;

public class InclusaoCozinhaMain {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(CursoEspecialistaApplication.class)
			.web(WebApplicationType.NONE)
			.run(args);
		
		CadastroCozinha cadatroCozinha = applicationContext.getBean(CadastroCozinha.class);
		
		Cozinha cozinha1 = new Cozinha();
		cozinha1.setNome("Arabe");
		
		Cozinha cozinha2 = new Cozinha();
		cozinha2.setNome("Boliviana");
		
		cadatroCozinha.adicionar(cozinha1);
		cadatroCozinha.adicionar(cozinha2);
				
	}
}
