package com.example.demo.api.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.model.Cozinha;
import com.example.demo.domain.repository.CozinhaRespository;

@RestController
@RequestMapping("/teste")
public class TesteController {
	
	@Autowired
	private CozinhaRespository cozinhaRepository;
	
//	@GetMapping("/cozinhas/por-nome")
//	public List<Cozinha> buscarPorNome(@RequestParam String nome){
//		return cozinhaRepository.consultarPorNome(nome);
//	}
}
