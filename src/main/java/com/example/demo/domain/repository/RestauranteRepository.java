package com.example.demo.domain.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.domain.model.Restaurante;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long>{
	
	List<Restaurante> getByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);
	
	//vai buscar no orm.xml a query dessa consulta	
	List<Restaurante> consultarPorNome(String nome, Long id);
	
	Optional<Restaurante> getFirstByNomeContaining(String nome);
	
	List<Restaurante> getTop2ByNomeContaining(String nome);
	
	int countByCozinhaId(Long id);
}