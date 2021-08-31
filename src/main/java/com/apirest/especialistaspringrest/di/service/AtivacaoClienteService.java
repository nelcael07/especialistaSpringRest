package com.apirest.especialistaspringrest.di.service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import com.apirest.especialistaspringrest.di.modelo.Cliente;
import com.apirest.especilistaspringrest.di.notificacao.NivelUrgencia;
import com.apirest.especilistaspringrest.di.notificacao.Notificador;
import com.apirest.especilistaspringrest.di.notificacao.TipoDoNotificador;
import com.apirest.especialistaspringrest.di.service.ClienteAtivacaoEvent;

@Component 
public class AtivacaoClienteService{
	
	// esse elemento do spring disponibiliza o disparo de eventos no spring	
	@Autowired
	private ApplicationEventPublisher eventPublisher;
	
	public void ativar(Cliente cliente){
		cliente.ativar();
		
		//chamando o evento		
		eventPublisher.publishEvent(new ClienteAtivacaoEvent(cliente));
		
	}
}
