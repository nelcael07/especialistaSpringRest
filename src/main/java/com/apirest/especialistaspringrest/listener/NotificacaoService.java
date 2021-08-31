package com.apirest.especialistaspringrest.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.apirest.especialistaspringrest.di.service.ClienteAtivacaoEvent;
import com.apirest.especilistaspringrest.di.notificacao.NivelUrgencia;
import com.apirest.especilistaspringrest.di.notificacao.Notificador;
import com.apirest.especilistaspringrest.di.notificacao.TipoDoNotificador;

@Component
public class NotificacaoService {
	
//	@TipoDoNotificador(NivelUrgencia.NORMAL)
//	@Autowired
//	private Notificador notificador;
	
	
	@EventListener
	public void clienteAtivadoListener(ClienteAtivacaoEvent event) {
		System.out.println("Cliente "+event.getCliente().getNome()+" agora está ativo");
//		notificador.notificar(event.getCliente(), "NOTIFICANDO");
	}
}
