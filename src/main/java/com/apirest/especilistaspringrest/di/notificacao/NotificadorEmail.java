package com.apirest.especilistaspringrest.di.notificacao;

import org.springframework.stereotype.Component;

import com.apirest.especialistaspringrest.di.modelo.Cliente;
import com.apirest.especialistaspringrest.di.service.Notificador;


public class NotificadorEmail implements Notificador{
	private boolean CaixaAlta;
	private String hostServidorSmtp;
	//construtor
	public NotificadorEmail(String hostServidorSmtp) {
		System.out.println("NotificarEmail");
		this.hostServidorSmtp = hostServidorSmtp;
	}
	
	public void setCaixaAlta(boolean caixaAlta) {
		CaixaAlta = caixaAlta;
	}

	@Override
	public void notificar(Cliente cliente, String mensagem){
		if(this.CaixaAlta) {
			mensagem = mensagem.toUpperCase();
		}
		System.out.printf("Notificar %s atráves do email %s usando o servidor %s: %s \n",
		cliente.getNome(), cliente.getEmail(), mensagem, this.hostServidorSmtp);
	}

}

