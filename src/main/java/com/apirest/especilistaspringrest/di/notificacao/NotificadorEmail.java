package com.apirest.especilistaspringrest.di.notificacao;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.apirest.especialistaspringrest.di.modelo.Cliente;

//anotação diz que esse arquivo rodara em prod - produção
@Profile("Prod")
//anotação personalizada
@TipoDoNotificador(NivelUrgencia.URGENTE)
@Component
public class NotificadorEmail implements Notificador{
	
	public NotificadorEmail() {
		System.out.println("Email Real");
	}
	
	@Override
	public void notificar(Cliente cliente, String mensagem){
		System.out.printf("Notificar %s atráves do SMS %s : %s \n",
		cliente.getNome(), cliente.getTelefone(), mensagem);
	}

}

