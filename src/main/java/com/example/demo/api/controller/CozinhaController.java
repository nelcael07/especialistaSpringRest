package com.example.demo.api.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.model.Cozinha;
import com.example.demo.domain.repository.CozinhaRespository;

//@Controller
////diz que a resposta dos metodos devem ir para o body da response da resquest
//@ResponseBody

// essa rest controller tem a controller e a responsebody
@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {
	
	@Autowired
	private CozinhaRespository cozinhaRespository;
	
	@GetMapping
	public List<Cozinha> listar(){
		return cozinhaRespository.listar();
	}
	
}
