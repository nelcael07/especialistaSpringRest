package com.injecaoDepencia.di.notificacao;

import com.injecaoDependencia.di.modelo.Cliente;

public class NotificadorSMS implements Notificador{
  // essa anotação diz que eu to subescrevendo um metodo
  @Override
  public void notificar(Cliente cliente, String mensagem){
    System.out.printf("Notificando %s por sms através do telefona %s: %s \n", 
    cliente.getNome(),cliente.getTelefone(), mensagem);
  }
}
