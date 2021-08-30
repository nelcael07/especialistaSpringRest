package com.apirest.especialistaspringrest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.apirest.especialistaspringrest.di.service.AtivacaoClienteService;

//maneira de configurar beans em uma class config

@Configuration
public class ServiceConfig {
		
	@Bean(initMethod = "init", destroyMethod = "destroy")
	public AtivacaoClienteService ativacaoClienteService() {
		return new AtivacaoClienteService(); 
	}
}
