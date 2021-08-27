package com.apirest.especialistaspringrest.di.service;



import java.util.List;

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
