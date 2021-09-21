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
import com.example.demo.domain.model.Produto;
import com.example.demo.domain.repository.ProdutoRepository;
import com.example.demo.domain.service.CadastroProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CadastroProdutoService produtoService;
	
	@GetMapping
	public List<Produto> listar(){
		return produtoRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Produto buscar(@PathVariable Long id) {
		return produtoService.buscar(id);
	}
	
	@PutMapping("/{id}")
	public Produto atualizar(@PathVariable Long id, @RequestBody Produto produto){
		Produto produtobuscado = produtoService.buscar(id);
		BeanUtils.copyProperties(produto, produtobuscado, "id");
		return produtoService.salvar(produtobuscado);
	}
	
	@PostMapping
	public Produto criar(@RequestBody Produto produto) {
		return produtoService.salvar(produto);
	}
	
	@DeleteMapping("/{id}")
	public void remover(@PathVariable Long id){
		produtoService.remover(id);
	}
	
	
}
