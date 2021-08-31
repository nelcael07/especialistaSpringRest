package com.apirest.especialistaspringrest.di.service;

import com.apirest.especialistaspringrest.di.modelo.Cliente;

public class ClienteAtivacaoEvent {
	private Cliente cliente;
	
	public ClienteAtivacaoEvent(Cliente cliente) {
		this.cliente = cliente;
	}

	public Cliente getCliente() {
		return cliente;
	}
	
	
}
