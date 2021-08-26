package com.apirest.especialistaspringrest.di.service;

import org.springframework.stereotype.Component;
import com.apirest.especialistaspringrest.di.modelo.Cliente;



public class AtivacaoClienteService{
	private Notificador notificador;
	//construtor
	public AtivacaoClienteService(Notificador notificador) {
	  this.notificador = notificador;
	  System.out.println("AtivacaoClienteService: "+ notificador);
	}
	
	public void ativar(Cliente cliente){
		cliente.ativar();
		notificador.notificar(cliente, "Seu cadastro no sistema está ativo!");
	}
}
