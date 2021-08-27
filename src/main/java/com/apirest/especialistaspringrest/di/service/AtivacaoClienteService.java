package com.apirest.especialistaspringrest.di.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.apirest.especialistaspringrest.di.modelo.Cliente;
import com.apirest.especilistaspringrest.di.notificacao.Notificador;

@Component
public class AtivacaoClienteService{
	
	@Autowired(required = false)
	private Notificador notificador;
	
	public void ativar(Cliente cliente){
		cliente.ativar();
		if(notificador!= null) {
			System.out.println("tem aqui");			
		}else {
			System.out.println("Não exite notificador, mas cliente foi ativado!");
		}
	}
}
