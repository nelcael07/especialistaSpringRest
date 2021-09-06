package com.example.demo.api.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@GetMapping("/{id}")
	public ResponseEntity<Cozinha> buscar(@PathVariable Long id) {
		Cozinha cozinha = cozinhaRespository.buscar(id);
		if (cozinha != null) {
			return ResponseEntity.ok(cozinha);
		}else {
		  return ResponseEntity.notFound().build();
		}
	}	
	
	//a requisição requestBody vincula o corpo da requisição com a instancia de cozinha criada	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cozinha adicionar(@RequestBody Cozinha cozinha){
		return cozinhaRespository.salvar(cozinha);
	}
	
}
