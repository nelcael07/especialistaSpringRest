package com.injecaoDependencia.di.service;

import com.injecaoDepencia.di.notificacao.Notificador;
import com.injecaoDependencia.di.modelo.Cliente;

public class AtivacaoClienteService {
	// construtor
	public AtivacaoClienteService(Notificador notificador) {
		this.notificador = notificador;
	}
	
	private Notificador notificador;


	public void ativar(Cliente cliente) {
		cliente.ativar();
		this.notificador.notificar(cliente, "Seu cadastro no sistema est� ativo!");
		
	}
}
