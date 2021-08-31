package com.apirest.especilistaspringrest.di.notificacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.apirest.especialistaspringrest.di.modelo.Cliente;

@TipoDoNotificador(NivelUrgencia.URGENTE)
@Component
public class NotificadorEmail implements Notificador{
	
	@Autowired
	private NotificadorProperties properties;
	
	
	@Override
	public void notificar(Cliente cliente, String mensagem){
			
		System.out.println("host: "+ properties.getHostServidor());
		System.out.println("porta: "+ properties.getPortaServidor());
		
		System.out.printf("Notificar %s atráves do email %s : %s \n",
		cliente.getNome(), cliente.getTelefone(), mensagem);
	}

}

