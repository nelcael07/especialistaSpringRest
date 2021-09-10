package com.example.demo.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import com.example.demo.domain.model.Restaurante;

public interface RestauranteRespositoryQueries {

	List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal);

}