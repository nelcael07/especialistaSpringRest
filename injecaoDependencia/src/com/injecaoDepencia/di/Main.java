package com.injecaoDepencia.di;

import com.injecaoDepencia.di.notificacao.Notificador;
import com.injecaoDepencia.di.notificacao.NotificadorEmail;
import com.injecaoDepencia.di.notificacao.NotificadorSMS;
import com.injecaoDependencia.di.modelo.Cliente;
import com.injecaoDependencia.di.service.AtivacaoClienteService;
import com.injecaoDependencia.di.service.EmissaoNotaFiscalService;

public class Main {
	public static void main(String[] args) {
		Cliente nelcael = new Cliente("Nelcael","nelcaif@gmail.com","011989753545");
		Cliente lud = new Cliente("lud","lud@gmail.com","011989753545");

		// instanciando um do tipo Notificador como NotificadorEmail, pois notificadorEmail implementa a interface notificador
		Notificador notificadorEmail = new NotificadorEmail();
		Notificador notificadorSMS = new NotificadorSMS();

		// injeção de dependencias
		AtivacaoClienteService notificaremail = new AtivacaoClienteService(notificadorEmail);
		notificaremail.ativar(nelcael);
		
	}
}
