package com.example.demo.infrastructure.respository.queries;

import java.util.List;

import com.example.demo.domain.model.Restaurante;

//O BENEFICIO DE USAR ESSA INTERFACE É QUE ELE ALERTA QUALQUER TIPO DE ERRO NO  METODO, OQ NÃO ACONTECIA COM A ASSINATURA NO REPOSITORY PADRÃO
public interface RestauranteRespositoryQueries {

//	List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal);
	
	List<Restaurante> findComFreteGratis(String nome);
	

}