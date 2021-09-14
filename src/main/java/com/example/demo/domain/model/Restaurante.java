package com.example.demo.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
	
	@JsonIgnoreProperties("hibernateLazyInitializer")
	//mandando a toOne carregar só se for necessario utilizando o lazy.
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn( nullable = false )
	private Cozinha cozinha;
	
	@JsonIgnore
	@Embedded
	private Endereco endereco;
	
	@JsonIgnore
	@CreationTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private LocalDateTime dataCadastro;

	@JsonIgnore
	@UpdateTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private LocalDateTime dataAtualizacao;
	 
	@JsonIgnore
	//fazendo ele ter um select ansioso.
	@ManyToMany(fetch = FetchType.EAGER )
 	@JoinTable(name = "restaurante_forma_pagamento", 
			 joinColumns = @JoinColumn(name = "restaurante_id"),
			 inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id")
	) 
	private List<FormaPagamento> formasPagamento = new ArrayList<>();
	
 	}	
