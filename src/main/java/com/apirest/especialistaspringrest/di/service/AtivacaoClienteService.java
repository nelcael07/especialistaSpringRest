package com.apirest.especialistaspringrest.di.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.apirest.especialistaspringrest.di.modelo.Cliente;

@Component
public class AtivacaoClienteService{
	//maneira de injetar dependencia
	//required false diz que a instancia de notificador não é fundamental, se tiver beleza, se não tiver ele funciona do mesmo jeito
	@Autowired(required = false)
	private Notificador notificador;
	
	public void ativar(Cliente cliente){
		cliente.ativar();
		
		if(notificador!= null) {
			notificador.notificar(cliente, "Seu cadastro no sistema está ativo!");			
		}else {
			System.out.println("Não exite notificador, mas cliente foi ativado!");
		}
	}


}
