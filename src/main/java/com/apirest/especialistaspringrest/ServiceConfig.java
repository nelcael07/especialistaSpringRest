package com.apirest.especialistaspringrest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.apirest.especialistaspringrest.di.service.AtivacaoClienteService;
import com.apirest.especialistaspringrest.di.service.Notificador;

@Configuration
public class ServiceConfig {
	@Bean
	public AtivacaoClienteService ativacaoClienteService(Notificador notificador) {
		return new AtivacaoClienteService(notificador);
	}
}
