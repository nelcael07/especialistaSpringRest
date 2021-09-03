package com.example.demo.domain.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.Data;
import lombok.EqualsAndHashCode;

//Nomear nome da tag primaria no quando o response é em xml
//@JsonRootName("teste")
@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cozinha {
	
	@EqualsAndHashCode.Include
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	// ignora esse atributo no json 		
	// @JsonIgnore
	// Para adicionar um novo nome desse item no json.		
	@JsonProperty(value = "titulo")
	@Column(nullable = false)
	private String nome;
	
}
