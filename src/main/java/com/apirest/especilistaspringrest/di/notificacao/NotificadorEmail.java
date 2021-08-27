package com.apirest.especilistaspringrest.di.notificacao;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.apirest.especialistaspringrest.di.modelo.Cliente;
import com.apirest.especialistaspringrest.di.service.Notificador;

//mandando dar prioridade para esse bean na injeção de dependencia, isso se dá quando tem uma injeção do tipo de interface e tem mais de uma classe implementando essa interface
@Primary
@Component
public class NotificadorEmail implements Notificador{
	
	@Override
	public void notificar(Cliente cliente, String mensagem){
		System.out.printf("Notificar %s atráves do SMS %s : %s \n",
		cliente.getNome(), cliente.getTelefone(), mensagem);
	}

}

