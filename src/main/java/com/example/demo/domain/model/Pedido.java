package com.example.demo.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.example.demo.domain.enuns.StatusPedido;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pedido {
	
	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	
	@JoinColumn(nullable = false)
	private BigDecimal taxaFrete;
	
	@JoinColumn(nullable = false)
	private BigDecimal valorTotal;
	
	@JoinColumn(nullable = false)
	private LocalDateTime dataCriacao;
	
	private LocalDateTime dataConfirmacao;
	
	private LocalDateTime dataCancelamento;
	
	private LocalDateTime dataEntrega;
	
	@Embedded
	private Endereco enderecoEntrega;
	
	@JoinColumn(nullable = false)
	private StatusPedido status_pedido;
	
	//relações	
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Restaurante restaurante;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Usuario cliente;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private FormaPagamento formaPagamento;
	
	
	@OneToMany
	private List<ItemPedido> itens;
	
	
	
	
	

}
