package com.apirest.especilistaspringrest.di.notificacao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.apirest.especialistaspringrest.di.modelo.Cliente;

@TipoDoNotificador(NivelUrgencia.URGENTE)
@Component
public class NotificadorEmail implements Notificador{
	
	//esse value busca o dados lá do properties passando exatamente o nome da configuração passada lá no arquivo de configuração	
	@Value("${notificador.email.host-servidor}")
	private String host;
	
	@Value("${notificador.email.porta-servidor}")
	private Integer porta;
	
	
	@Override
	public void notificar(Cliente cliente, String mensagem){
			
		System.out.println("host: "+ host);
		System.out.println("porta: "+ porta);
		
		System.out.printf("Notificar %s atráves do email %s : %s \n",
		cliente.getNome(), cliente.getTelefone(), mensagem);
	}

}

