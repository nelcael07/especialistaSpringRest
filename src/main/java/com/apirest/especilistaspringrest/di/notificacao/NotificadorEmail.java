package com.apirest.especilistaspringrest.di.notificacao;

import com.apirest.especialistaspringrest.di.modelo.Cliente;

public class NotificadorEmail {

  public void notificar(Cliente cliente, String mensagem){
    System.out.printf("Notificar %s atráves do email %s: %s \n",
    cliente.getNome(), cliente.getEmail(), mensagem
    );
  }
}

