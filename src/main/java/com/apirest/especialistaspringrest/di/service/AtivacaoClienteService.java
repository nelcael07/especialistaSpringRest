package com.apirest.especialistaspringrest.di.service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.apirest.especialistaspringrest.di.modelo.Cliente;
import com.apirest.especilistaspringrest.di.notificacao.NivelUrgencia;
import com.apirest.especilistaspringrest.di.notificacao.Notificador;
import com.apirest.especilistaspringrest.di.notificacao.TipoDoNotificador;

//@Component 
public class AtivacaoClienteService{
	
	//função init de calback
//	@PostConstruct
	public void init() {
		System.out.println("entrou no init "+ notificador);
	}
	
//	função  destroy de calback
// 	@PreDestroy
	public void destroy() {
		System.out.println("entrou no destroy "+ notificador);
	}
	
	
	@TipoDoNotificador(NivelUrgencia.NORMAL)
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
