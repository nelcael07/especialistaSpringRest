package com.apirest.especialistaspringrest.di.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.apirest.especialistaspringrest.di.modelo.Cliente;

@Component
public class AtivacaoClienteService{
	//maneira de injetar dependencia 
	@Autowired
	private Notificador notificador;

	
	// anotação que disk qual é o construtor que será usado para injeção de dependencia
	//@Autowired
	//injeção de dependencia com metodo set
	//public void setNotificador(Notificador notificador) {
		//this.notificador = notificador;
	//}
	
	public void ativar(Cliente cliente){
		cliente.ativar();
		notificador.notificar(cliente, "Seu cadastro no sistema está ativo!");
	}


}
