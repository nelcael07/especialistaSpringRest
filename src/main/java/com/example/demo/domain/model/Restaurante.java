package com.example.demo.domain.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Restaurante {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String nome;
		
	@Column(name="taxa_frete",nullable = false)
	private BigDecimal taxaFrete;
	
	@ManyToOne	
	@JoinColumn( nullable = false )
	private Cozinha cozinha;
	
	// o nome da coluna de junção de relacionamento vai ser restaurante_forma_pagamento.
	//o nome da coluna que vai representar a relação nessa tabela é restaurante_id.	
	@ManyToMany
 	@JoinTable(name = "restaurante_forma_pagamento", 
			 //NOME DA COLUNA QUE VAI REPRESENTAR O RESTAURANTE.
			 joinColumns = @JoinColumn(name = "restaurante_id"),
			 //O NOME DA COLUNA QUE VAI REPRESENTAR A FORMA DE PAGAMENTO.			 
			 inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id")
	)
	private List<FormaPagamento> formasPagamento = new ArrayList<>();
	
	
}	
