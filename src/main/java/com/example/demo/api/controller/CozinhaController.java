package com.example.demo.api.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.domain.model.Cozinha;
import com.example.demo.domain.repository.CozinhaRespository;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {
	
	@Autowired
	private CozinhaRespository cozinhaRespository;
	
	//COMO PADRÃO O SPRING USA JSON		
	//posso especificar em cada metodo do controller qual formato ele pode ser exportado.	
	 //@GetMapping(produces = "application/json")
	
	@GetMapping 
	public List<Cozinha> listar(){
		return cozinhaRespository.listar();
	}
}
