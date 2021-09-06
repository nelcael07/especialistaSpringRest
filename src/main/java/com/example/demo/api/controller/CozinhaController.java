package com.example.demo.api.controller;

import java.awt.PageAttributes.MediaType;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.api.model.CozinhasXmlWrapper;
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
	
	//ANOTAÇÃO DIZ QUE QUANDO O TRAFEGO ESTIVER SENDO POR XML, ELE VAI EXECUTAR ESSE METODO	
//	@GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
	public CozinhasXmlWrapper listarXml(){ 
		return new CozinhasXmlWrapper(cozinhaRespository.listar());
	}
	
	@GetMapping("/{id}")
	public Cozinha buscar(@PathVariable Long id) {
		System.out.println(id);
		return cozinhaRespository.buscar(id);
	}
}
