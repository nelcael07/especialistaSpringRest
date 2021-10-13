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

import com.example.demo.api.assembler.EstadoModelAssembler;
import com.example.demo.api.assembler.EstadoModelDiassembler;
import com.example.demo.api.model.CidadeModel;
import com.example.demo.api.model.EstadoModel;
import com.example.demo.api.model.input.EstadoInput;
import com.example.demo.domain.exception.EstadoNaoEncontradoException;
import com.example.demo.domain.exception.NegocioException;
import com.example.demo.domain.model.Cidade;
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
	
	@Autowired
	private EstadoModelAssembler estadoAssembler;
	
	@Autowired
	private EstadoModelDiassembler estadoDiassembler;
	
	
	@GetMapping
	public List<EstadoModel> listar(){
		return estadoAssembler.convertListEstadoModel(estadoRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public EstadoModel buscar(@PathVariable Long id) {
		EstadoModel estadoModel = new EstadoModel();
		estadoModel = estadoAssembler.convertEstadoModel(cadastroEstado.buscar(id));
		return estadoModel;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EstadoModel criar(@RequestBody @Valid EstadoInput estadoInput){
		return estadoAssembler.convertEstadoModel(cadastroEstado.salvar(estadoDiassembler.convertEstado(estadoInput))); 
	}
	
	@PutMapping("/{id}")
	public EstadoModel atualizar (@RequestBody @Valid EstadoInput estadoInput, @PathVariable Long id) {
		Estado estadobuscado = cadastroEstado.buscar(id);
		estadoDiassembler.updateBeanUtils(estadoInput, estadobuscado);
		return estadoAssembler.convertEstadoModel(cadastroEstado.salvar(estadobuscado));
	}
	
	
	
//	Cidade cidadebuscada = cidadeService.buscar(id);
//	cidadeDiassembler.copyDomainObject(cidadeInput, cidadebuscada);
//	try{
//		CidadeModel cidadeModel = new CidadeModel();
//		cidadeModel = cidadeAssembler.toModel(cidadeService.salvar(cidadebuscada));
//		return cidadeModel;
//	}catch (EstadoNaoEncontradoException e) {
//		throw new NegocioException(e.getMessage(), e);
//	}
	
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id){
		cadastroEstado.remover(id);
	}
	
	
	
}
