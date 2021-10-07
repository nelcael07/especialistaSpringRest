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
import com.example.demo.api.assembler.RestauranteModelAssembler;
import com.example.demo.api.model.RestauranteModel;
import com.example.demo.api.model.input.RestauranteInput;
import com.example.demo.domain.exception.CozinhaNaoEncontradoException;
import com.example.demo.domain.exception.EntidadeNaoEncontradaException;
import com.example.demo.domain.exception.NegocioException;
import com.example.demo.domain.model.Cozinha;
import com.example.demo.domain.model.Restaurante;
import com.example.demo.domain.repository.RestauranteRepository;
import com.example.demo.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CadastroRestauranteService cadastroRestaurante;
	
	@Autowired
	private RestauranteModelAssembler restauranteAssembler;
	
	
	@GetMapping
	public List<RestauranteModel> listar(){
		return restauranteAssembler.toColletionModel(restauranteRepository.findAll());
	} 
	
	@GetMapping("/{id}")
	public RestauranteModel buscar(@PathVariable Long id) {
		Restaurante restaurante = cadastroRestaurante.buscar(id);
		
		return restauranteAssembler.toModel(restaurante);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public RestauranteModel adicionar(@RequestBody @Valid RestauranteInput restaurante) {
		try {
			Restaurante restauranteObject = toDomainObject(restaurante);
			return restauranteAssembler.toModel(cadastroRestaurante.salvar(restauranteObject));
		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}
	
	@DeleteMapping("/{id}")
	public void remover (@PathVariable Long id) {
		cadastroRestaurante.remover(id);
	}
	
	@PutMapping("/{id}")
	public RestauranteModel atualizar(@PathVariable Long id, @RequestBody @Valid RestauranteInput restauranteInput){
		try {
			Restaurante restaurante = toDomainObject(restauranteInput);
			Restaurante restaurantebuscado = cadastroRestaurante.buscar(id);
			BeanUtils.copyProperties(restaurante, restaurantebuscado, "id", "dataCadastro");
			return restauranteAssembler.toModel(cadastroRestaurante.salvar(restaurantebuscado));
		} catch (CozinhaNaoEncontradoException e) {
			throw new NegocioException(e.getMessage());
		}
	}
	
	private Restaurante toDomainObject(RestauranteInput restauranteInput) {
		Restaurante restaurante = new Restaurante();
		
		restaurante.setNome(restauranteInput.getNome());
		restaurante.setTaxaFrete(restauranteInput.getTaxaFrete());
		Cozinha cozinha = new Cozinha();
		cozinha.setId(restauranteInput.getCozinha().getId());
		cozinha.setNome(restauranteInput.getNome());
		restaurante.setCozinha(cozinha);
		
		return restaurante;
	}
	
}

	
