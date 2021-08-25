package com.injecaoDepencia.di.notificacao;

import com.injecaoDependencia.di.modelo.Cliente;

public class NotificadorEmail implements Notificador{
	@Override
	public void notificar (Cliente cliente, String mensagem) {
		System.out.printf("Notificando %s atrav�s do email %s: %s \n", 
		cliente.getNome(), cliente.getEmail(), mensagem);
	}
}
