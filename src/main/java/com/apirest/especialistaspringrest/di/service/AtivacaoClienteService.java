package com.apirest.especialistaspringrest.di.service;

import com.apirest.especialistaspringrest.di.modelo.Cliente;
import com.apirest.especilistaspringrest.di.notificacao.NotificadorEmail;

public class AtivacaoClienteService {
  private NotificadorEmail notificador;

  public void ativar(Cliente cliente){
    cliente.ativar();

    notificador.notificar(cliente, "Seu cadastro no sistema está ativo!");
  }
}
