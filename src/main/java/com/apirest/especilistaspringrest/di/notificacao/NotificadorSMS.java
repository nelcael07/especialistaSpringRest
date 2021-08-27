package com.apirest.especilistaspringrest.di.notificacao;

import org.springframework.stereotype.Component;

import com.apirest.especialistaspringrest.di.modelo.Cliente;

@TipoDoNotificador(NivelUrgencia.NORMAL)
@Component
public class NotificadorSMS implements Notificador{
	
	@Override
	public void notificar(Cliente cliente, String mensagem){
		System.out.printf("Notificar %s atráves do email %s : %s \n",
		cliente.getNome(), cliente.getEmail(), mensagem);
	}
}
