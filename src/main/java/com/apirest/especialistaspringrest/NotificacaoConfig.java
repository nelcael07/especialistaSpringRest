package com.apirest.especialistaspringrest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.apirest.especilistaspringrest.di.notificacao.NotificadorEmail;

@Configuration
public class NotificacaoConfig {

	@Bean
	public NotificadorEmail notificadorEmail() {
		NotificadorEmail notificador = new NotificadorEmail("smtp.apirest.com.br");
		notificador.setCaixaAlta(true);
		return notificador;
	}
	
}
