package com.example.demo.domain.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.domain.model.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {

}
