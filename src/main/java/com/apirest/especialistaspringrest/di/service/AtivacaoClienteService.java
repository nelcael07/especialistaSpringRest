package com.apirest.especialistaspringrest.di.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import com.apirest.especialistaspringrest.di.modelo.Cliente;
import com.apirest.especilistaspringrest.di.notificacao.Notificador;




@Component
public class AtivacaoClienteService{
	
	@Autowired(required = false)
	private Notificador notificador;
	
	public void ativar(Cliente cliente){
		cliente.ativar();
		
//		for(Notificador notificador: notificadores) {
//			notificador.notificar(cliente, "SEU CADASTRO NO SISTEMA ESTÁ ATIVO");
//		}
		
		if(notificador!= null) {
			System.out.println("tem aqui");			
		}else {
			System.out.println("Não exite notificador, mas cliente foi ativado!");
		}
	}


}
