package com.example.demo.domain.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//diz que essa class é uma entidade que do banco de dados.
@Entity
//especifica um nome para a tabela no banco de dados, se não tiver nada, como default ele é o nome da class
@Table(name="tab_cozinhas")
public class Cozinha {
	
	//anotação que destaca o identificador da tabela	
	@Id
	private Long id;
	
	//esse atributo vai representar uma coluna, pode colocar um nome ou usar o nome do atributo como defaut, para isso é só não colocar nada.
	//se eu deixar sem o @Column também, por a classe estar com o entity ele já identifica que todos os atributos são colunas.	
	@Column(name="nom_cozinha")
	private String nome;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cozinha other = (Cozinha) obj;
		return Objects.equals(id, other.id);
	}

	
	
	
	
	
}
