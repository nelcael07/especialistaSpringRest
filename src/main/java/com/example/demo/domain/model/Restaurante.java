package com.example.demo.domain.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
	
	// essa propriedade  é uma propriedade compartilhada, um @Embeddable, é de um tipo incorporado.
	@Embedded
	private Endereco endereco;
	
	
	@JsonIgnore
	@ManyToMany
 	@JoinTable(name = "restaurante_forma_pagamento", 
			 joinColumns = @JoinColumn(name = "restaurante_id"),
			 inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id")
	) 
	private List<FormaPagamento> formasPagamento = new ArrayList<>();
	
}	
