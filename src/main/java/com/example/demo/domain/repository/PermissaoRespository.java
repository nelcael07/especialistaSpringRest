package com.example.demo.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.model.Permissao;

public interface PermissaoRespository extends JpaRepository<Permissao, Long>{
	
}
