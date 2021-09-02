package com.example.demo.domain.model;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

// gera gets e sets
//@Getter
//@Setter
//@Entity
//gerando equals e hashCode

//data gera get set e equals e hashcode
@Data
//deixo o equals e hash para gerar somente para os que eu deixar explicito por que se deixar pelo data ele gera o hascode com todos os atributos
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cozinha {
	
	//to incluinod o id no equals e hash code	
	@EqualsAndHashCode.Include
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
}
