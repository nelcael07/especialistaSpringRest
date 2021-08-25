package com.injecaoDependencia.di.service;

import com.injecaoDepencia.di.notificacao.Notificador;
import com.injecaoDepencia.di.notificacao.NotificadorEmail;
import com.injecaoDepencia.di.notificacao.NotificadorSMS;
import com.injecaoDependencia.di.modelo.Cliente;
import com.injecaoDependencia.di.modelo.Produto;

public class EmissaoNotaFiscalService {

	private Notificador notificador;
	public EmissaoNotaFiscalService(Notificador notificador){
		this.notificador = notificador;
	}

	public void emitir(Cliente cliente, Produto produto) {
		this.notificador.notificar(cliente, "Foi emitida a nota fiscal do produto: " + produto.getNome());
	}
}
