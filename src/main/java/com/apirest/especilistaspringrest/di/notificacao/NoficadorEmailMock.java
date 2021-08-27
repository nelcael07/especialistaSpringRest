package com.apirest.especilistaspringrest.di.notificacao;

//anotation
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

// class
import com.apirest.especialistaspringrest.di.modelo.Cliente;

//especifico o profile que esse arquivo vai rodar, a configuração de qual profile o projeto vai usar está no applicaton.properties
//tem como passar o prod por linha de comando tbm, está na aula 2.20
@Profile("dev")
//anotação personalizada
@TipoDoNotificador(NivelUrgencia.URGENTE)
@Component
public class NoficadorEmailMock implements Notificador {
	
	public NoficadorEmailMock () {
		System.out.println("Email mock");
	}
	
	@Override
	public void notificar(Cliente cliente, String mensagem){
		System.out.printf("Simulação Mock: Notificar %s atráves do SMS %s : %s \n",
		cliente.getNome(), cliente.getTelefone(), mensagem);
	}

}
