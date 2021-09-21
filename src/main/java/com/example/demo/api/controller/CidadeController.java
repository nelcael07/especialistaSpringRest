package com.example.demo.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.exception.EntidadeNaoEncontradaException;
import com.example.demo.domain.model.Cidade;
import com.example.demo.domain.repository.CidadeRepository;
import com.example.demo.domain.service.CadastroCidadeService;

@RestController
@RequestMapping("/cidades")
public class CidadeController {
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private CadastroCidadeService cidadeService;
	
	@GetMapping
	public List<Cidade> listar() {
		return cidadeRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Cidade buscar(@PathVariable Long id){
		return cidadeService.buscar(id);
	}
	
	@PutMapping("/{id}")
	public Cidade atualizar(@PathVariable Long id, @RequestBody Cidade cidade){
		Cidade cidadebuscada = cidadeService.buscar(id);
		BeanUtils.copyProperties(cidade, cidadebuscada, "id");
		return cidadeService.salvar(cidadebuscada);
	}
	
	@PostMapping
	public Cidade criar(@RequestBody Cidade cidade) {
		return cidadeService.salvar(cidade);
	}
	
	@DeleteMapping("/{id}")
	public void remover(@PathVariable Long id) {
		cidadeService.remover(id);
	}
	
	
}
