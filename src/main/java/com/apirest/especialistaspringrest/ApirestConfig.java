package com.apirest.especialistaspringrest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.apirest.especialistaspringrest.di.service.AtivacaoClienteService;
import com.apirest.especilistaspringrest.di.notificacao.NotificadorEmail;

//@Configuration
public class ApirestConfig {
	
	@Bean
	public NotificadorEmail notificadorEmail() {
		NotificadorEmail notificador = new NotificadorEmail("smtp.apirest.com.br");
		notificador.setCaixaAlta(true);
		return notificador;
	}
	
	@Bean
	public AtivacaoClienteService ativacaoClienteService() {
		return new AtivacaoClienteService(notificadorEmail());
	}
	
}
