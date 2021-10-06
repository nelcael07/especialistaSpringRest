package com.example.demo.api.mixin;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import com.example.demo.domain.model.Cozinha;
import com.example.demo.domain.model.Endereco;
import com.example.demo.domain.model.FormaPagamento;
import com.example.demo.domain.model.Produto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class RestauranteMixin {
	
 	@JsonIgnoreProperties(value = "nome", allowGetters = true)
	private Cozinha cozinha;
	
	@JsonIgnore
	private Endereco endereco;
	
//	@JsonIgnore
	private OffsetDateTime dataCadastro;

//	@JsonIgnore
	private OffsetDateTime dataAtualizacao;
	 
	@JsonIgnore
	private List<FormaPagamento> formasPagamento = new ArrayList<>();
	
 	@JsonIgnore
	private List<Produto> produto = new ArrayList<>();
}
