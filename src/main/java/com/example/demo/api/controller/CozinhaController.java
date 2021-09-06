package com.example.demo.api.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.domain.model.Cozinha;
import com.example.demo.domain.repository.CozinhaRespository;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

	@Autowired
	private CozinhaRespository cozinhaRespository;

	@GetMapping 
	public List<Cozinha> listar(){ 
		return cozinhaRespository.listar();
	}
	
	//como editar o status code que vai ser retornado nesse serviço 
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/{id}")
	public Cozinha buscar(@PathVariable Long id) {
		return cozinhaRespository.buscar(id);
	}
}
