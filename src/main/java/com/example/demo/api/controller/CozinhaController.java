package com.example.demo.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.domain.exception.EntidadeEmUsoException;
import com.example.demo.domain.exception.EntidadeNaoEncontradaException;
import com.example.demo.domain.model.Cozinha;
import com.example.demo.domain.repository.CozinhaRespository;
import com.example.demo.domain.service.CadastroCozinhaService;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

	@Autowired
	private CozinhaRespository cozinhaRespository;

	@Autowired
	private CadastroCozinhaService cadastroCozinha;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cozinha adicionar(@RequestBody Cozinha cozinha){
		return cadastroCozinha.salvar(cozinha);
	}
	
//	@DeleteMapping("/{id}")
//	public ResponseEntity<?> remover(@PathVariable Long id) {
//		try {
//			cadastroCozinha.remover(id);
//			return ResponseEntity.noContent().build();
//		}catch (EntidadeNaoEncontradaException e) {
//			return ResponseEntity.notFound().build();
//		}catch (EntidadeEmUsoException e) {
//			se estiver com erro ele retorna um responseEntity não uma excessão
//			return ResponseEntity.status(HttpStatus.CONFLICT).build();
//		}
//	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		try {
			cadastroCozinha.remover(id);
		} catch (EntidadeNaoEncontradaException e ) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}catch(EntidadeEmUsoException e) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Cozinha> atualizar(@PathVariable Long id, @RequestBody Cozinha cozinha) {
		Optional<Cozinha> cozinhabusca = cozinhaRespository.findById(id);
		if (cozinhabusca.isPresent()) {
			BeanUtils.copyProperties(cozinha, cozinhabusca.get(), "id");
			Cozinha cozinhaCreated= cadastroCozinha.salvar(cozinhabusca.get());
			return ResponseEntity.ok(cozinhaCreated);
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Cozinha> buscar(@PathVariable Long id) {
		Optional<Cozinha> cozinha = cozinhaRespository.findById(id);
		if (cozinha.isPresent()) {
			return ResponseEntity.ok(cozinha.get());
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping 
	public List<Cozinha> listar(){ 
		return cozinhaRespository.findAll();
	}
	
	
}
