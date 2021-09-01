package com.example.demo.jpa;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;

import com.example.demo.CursoEspecialistaApplication;
import com.example.demo.domain.model.Cozinha;

public class AlteracaoCozinhaMain {
	ApplicationContext applicationContext = new SpringApplicationBuilder(CursoEspecialistaApplication.class)
			.web(WebApplicationType.NONE)
			.run(args); 
		
		CadastroCozinha cadatroCozinha = applicationContext.getBean(CadastroCozinha.class);
		
		Cozinha cozinha = new Cozinha(); 
		//adiciono o id que está na cozinha que eu quero atualizar		
		cozinha.setId(1L);
		cozinha.setNome("Brasileira");
		
		
		//esse metodo faz uma busca no banco, se a instacia mandada já tiver o id setado, ele busca esse id e atualiza esse campo, 
		//se não tiver ele cria no banco com o auto increment		
		cadastroCozinha.salvar(cozinha);
		
		
		 
		
}
