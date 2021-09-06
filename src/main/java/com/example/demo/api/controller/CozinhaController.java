package com.example.demo.api.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Cozinha> buscar(@PathVariable Long id) {
		Cozinha cozinha =  cozinhaRespository.buscar(id);
		
//		return ResponseEntity.status(HttpStatus.OK).body(cozinha);
		//outra maneira de retornar o status code, agora com o ResponseEntity, esse recurso é possitivo quando se usa condicional(se der isso retorna isso....)		
//		return ResponseEntity.ok(cozinha);
		
		//fazendo configurações no header da response		
		//dizendo que esse recurso foi movido para outro local;		
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.LOCATION, "http:\\localhost:3000\cozinhas");
		
		return ResponseEntity.status(HttpStatus.FOUND).headers(headers).build());
		
	}
}
