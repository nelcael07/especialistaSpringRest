package com.apirest.especilistaspringrest.di.notificacao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
// to dizendo com essa anotação que essa é uma classe de configuração que vai configurar as propriedades com a raiz notificador.email
@ConfigurationProperties("notificador.email")
public class NotificadorProperties {
	//as propriedades perdem o - e ficam com os separadores maiusculos
	private String hostServidor;
	
	private Integer portaServidor;

	//	get and set
	public String getHostServidor() {
		return hostServidor;
	}

	public void setHostServidor(String hostServidor) {
		this.hostServidor = hostServidor;
	}

	public Integer getPortaServidor() {
		return portaServidor;
	}

	public void setPortaServidor(Integer portaServidor) {
		this.portaServidor = portaServidor;
	}
	
	
	
	
	
	
}
