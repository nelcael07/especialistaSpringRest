package com.example.demo.respository.queries;

import java.math.BigDecimal;
import java.util.List;
import com.example.demo.domain.model.Restaurante;

//O BENEFICIO DE USAR ESSA INTERFACE É QUE ELE ALERTA QUALQUER TIPO DE ERRO NO  METODO, OQ NÃO ACONTECIA COM A ASSINATURA NO REPOSITORY PADRÃO
public interface RestauranteRespositoryQueries {

	List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal);

}