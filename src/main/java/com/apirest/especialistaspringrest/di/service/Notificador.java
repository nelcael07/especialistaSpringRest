package com.apirest.especialistaspringrest.di.service;

import com.apirest.especialistaspringrest.di.modelo.Cliente;

public interface Notificador {
	void notificar(Cliente cliente, String mensagem);
}