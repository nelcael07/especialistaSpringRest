package com.example.demo.infrastructure.repository.spec;

import java.math.BigDecimal;

import org.springframework.data.jpa.domain.Specification;

import com.example.demo.domain.model.Restaurante;

public class RestauranteSpecs {
	
	//to fazendo um metodo estatico no qual eu implemento a interface e tenho que implementar o metodo Predicate que está com arrow ai.	
	public static Specification<Restaurante> comFreteGratis(){
		return (root, query, builder) -> builder.equal(root.get("taxaFrete"), BigDecimal.ZERO);
	}
	
	public static Specification<Restaurante> comNomeSemelhante(String nome){
		return (root, query, builder) -> builder.like(root.get("nome"), "%"+ nome +"%");
	}
	
}
