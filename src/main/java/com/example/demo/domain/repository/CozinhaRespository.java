package com.example.demo.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.model.Cozinha;

@Repository
public interface CozinhaRespository extends CustomJpaRepository<Cozinha, Long> {

	//O spring data jpa tem algumas palavras chaves que podem ajudar ao fazer as consultas,
	//o containing por exemplo, faz a função do like que é de buscar por palavras que contem aquele conjunto	
	List<Cozinha> findByNomeContaining(String nome);
	
	Optional<Cozinha> findUnicoByNome(String nome);
	
	boolean existsByNome(String nome);
	
	
}
