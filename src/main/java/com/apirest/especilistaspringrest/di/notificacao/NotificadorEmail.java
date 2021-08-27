package com.apirest.especilistaspringrest.di.notificacao;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.apirest.especialistaspringrest.di.modelo.Cliente;
import com.apirest.especialistaspringrest.di.service.Notificador;

//usando qualifier para tratar a ambiguidade
@Qualifier("email")
@Component
public class NotificadorEmail implements Notificador{
	
	@Override
	public void notificar(Cliente cliente, String mensagem){
		System.out.printf("Notificar %s atráves do SMS %s : %s \n",
		cliente.getNome(), cliente.getTelefone(), mensagem);
	}

}

