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
	 
	private static final String MSG_PRODUTO_NÃO_ENCONTRADO = "Produto %d não foi encontrado";
	

	@Autowired
	private ProdutoRepository produtoRespository;
	
	@Autowired
	private CadastroRestauranteService cadastroRestaurante;
	
	public Produto salvar(Produto produto) {
		Long id  = produto.getRestaurante().getId();
		Restaurante restaurante = cadastroRestaurante.buscar(id);
		produto.setRestaurante(restaurante);
		return produtoRespository.save(produto);
	}
	
	public void remover(Long id) {
		try {
			produtoRespository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.format(MSG_PRODUTO_NÃO_ENCONTRADO, id));
		}
	}
	
	public Produto buscar(Long id) {
		return produtoRespository.findById(id).orElseThrow(
				() -> new EntidadeNaoEncontradaException(String.format(MSG_PRODUTO_NÃO_ENCONTRADO,id)));
	}
	
	
}
