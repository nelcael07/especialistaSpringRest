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

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cozinha {
	
	@EqualsAndHashCode.Include
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
}
