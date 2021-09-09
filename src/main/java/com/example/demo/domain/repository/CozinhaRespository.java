package com.example.demo.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.model.Cozinha;

@Repository
public interface CozinhaRespository extends JpaRepository<Cozinha, Long> {

	// o nome do metodo tem que ser com o nome de alguma propriedade.	
	List<Cozinha> findByNome(String nome); 
	Optional<Cozinha> findUnicoByNome(String nome);
}
