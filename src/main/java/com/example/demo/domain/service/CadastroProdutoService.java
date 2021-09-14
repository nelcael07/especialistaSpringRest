package com.example.demo.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.domain.exception.EntidadeNaoEncontradaException;
import com.example.demo.domain.model.Produto;
import com.example.demo.domain.model.Restaurante;
import com.example.demo.domain.repository.ProdutoRepository;
import com.example.demo.domain.repository.RestauranteRepository;

@Service
public class CadastroProdutoService {
	 
	@Autowired
	private ProdutoRepository produtoRespository;
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	
	public Produto save(Produto produto) {
		Long id  = produto.getRestaurante().getId();
		Restaurante restaurante = restauranteRepository.findById(id).orElseThrow(
				() -> new EntidadeNaoEncontradaException("Não existe restaurante com esse id")
		);
		produto.setRestaurante(restaurante);
		return produtoRespository.save(produto);
	}
	
	public void remover(Long id) {
		try {
			produtoRespository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.format("Produto %d não encontrado", id));
		}
	}
	
	
}
