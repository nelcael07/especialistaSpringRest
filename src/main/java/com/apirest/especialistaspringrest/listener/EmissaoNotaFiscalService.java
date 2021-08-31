package com.apirest.especialistaspringrest.listener;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.apirest.especialistaspringrest.di.service.ClienteAtivacaoEvent;

@Component
public class EmissaoNotaFiscalService {
	
	@EventListener
	public void clienteAtivadoListener(ClienteAtivacaoEvent event) {
		System.out.println("Emitindo nota fiscal para cliente "+event.getCliente().getNome());
	}
}
