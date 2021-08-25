package com.injecaoDepencia.di.notificacao;

import com.injecaoDependencia.di.modelo.Cliente;

public interface Notificador {
  void notificar(Cliente cliente, String mensagem);
}