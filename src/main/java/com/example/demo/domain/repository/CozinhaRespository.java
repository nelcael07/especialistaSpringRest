package com.example.demo.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.domain.model.Cozinha;

@Repository
public interface CozinhaRespository extends JpaRepository<Cozinha, Long> {

//	List<Cozinha> consultarPorNome(String nome);
	
}
