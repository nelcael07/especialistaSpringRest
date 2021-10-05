package com.example.demo.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.exception.EntidadeNaoEncontradaException;
import com.example.demo.domain.exception.ProdutoNaoEncontradoException;
import com.example.demo.domain.model.Produto;
import com.example.demo.domain.model.Restaurante;
import com.example.demo.domain.repository.ProdutoRepository;
import com.example.demo.domain.repository.RestauranteRepository;

@Service
public class CadastroProdutoService {
	

	@Autowired
	private ProdutoRepository produtoRespository;
	
	@Autowired
	private CadastroRestauranteService cadastroRestaurante;
	
	@Transactional
	public Produto salvar(Produto produto) {
		Long id  = produto.getRestaurante().getId();
		Restaurante restaurante = cadastroRestaurante.buscar(id);
		produto.setRestaurante(restaurante);
		return produtoRespository.save(produto);
	}
	
	@Transactional
	public void remover(Long id) {
		try {
			produtoRespository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ProdutoNaoEncontradoException(id);
		}
	}
	
	public Produto buscar(Long id) {
		return produtoRespository.findById(id).orElseThrow(
				() -> new ProdutoNaoEncontradoException(id));
	}
	
	
}
