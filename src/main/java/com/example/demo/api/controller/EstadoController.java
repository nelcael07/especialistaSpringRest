package com.example.demo.api.controller;

import java.util.List;

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

import com.example.demo.domain.exception.EntidadeEmUsoException;
import com.example.demo.domain.exception.EntidadeNaoEncontradaException;
import com.example.demo.domain.model.Estado;
import com.example.demo.domain.repository.EstadoRepository;
import com.example.demo.domain.service.CadastroEstadoService;

@RestController
@RequestMapping("/estados")
public class EstadoController {
	
	@Autowired
	private CadastroEstadoService cadastroEstado;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@GetMapping
	public List<Estado> listar(){
		return estadoRepository.listar();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Estado> buscar(@PathVariable Long id) {
		Estado estado = estadoRepository.buscar(id);
		if (estado == null) {
			return ResponseEntity.notFound().build() ;
		}
		return ResponseEntity.ok(estado);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Estado criar(@RequestBody Estado estado){
		return cadastroEstado.salvar(estado);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Estado> atualizar (@RequestBody Estado estado, @PathVariable Long id) {
		Estado estadobusca = estadoRepository.buscar(id);
		if (estadobusca != null) {
			BeanUtils.copyProperties(estado, estadobusca,"id");
			cadastroEstado.salvar(estadobusca);
			return ResponseEntity.ok(estadobusca);
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Estado> remover(@PathVariable Long id){
		try {
			cadastroEstado.remover(id);
			return ResponseEntity.noContent().build();
		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	
	
}
