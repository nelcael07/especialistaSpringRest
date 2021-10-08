package com.example.demo.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.api.assembler.CidadeModelAssembler;
import com.example.demo.api.model.CidadeModel;
import com.example.demo.domain.exception.EstadoNaoEncontradoException;
import com.example.demo.domain.exception.NegocioException;
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
	
	@Autowired
	private CidadeModelAssembler cidadeAssembler;
	
	@GetMapping
	public List<CidadeModel> listar() {
		return cidadeAssembler.toList(cidadeRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public CidadeModel buscar(@PathVariable Long id){
		CidadeModel cidadeModel = new CidadeModel();
		Cidade cidade = cidadeService.buscar(id);
		return  cidadeAssembler.toModel(cidade);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CidadeModel criar(@RequestBody @Valid Cidade cidade) {
		try {
			return cidadeAssembler.toModel(cidadeService.salvar(cidade));
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	@PutMapping("/{id}")
	public CidadeModel atualizar(@PathVariable Long id, @RequestBody @Valid Cidade cidade){
		Cidade cidadebuscada = cidadeService.buscar(id);
		BeanUtils.copyProperties(cidade, cidadebuscada, "id");
		try{
			CidadeModel cidadeModel = new CidadeModel();
			cidadeModel = cidadeAssembler.toModel(cidadeService.salvar(cidadebuscada));
			return cidadeModel;
		}catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		cidadeService.remover(id);
	}
	
}
