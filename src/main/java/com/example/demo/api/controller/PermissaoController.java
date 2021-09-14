package com.example.demo.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.model.Permissao;
import com.example.demo.domain.repository.PermissaoRespository;

@RestController
@RequestMapping("/permissoes")
public class PermissaoController {
	
	@Autowired
	private PermissaoRespository permissaoRepository;
	
	@GetMapping
	public List<Permissao> listar (){
		return permissaoRepository.findAll();
	}
	
	
}
