package com.example.demo.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.domain.exception.EntidadeNaoEncontradaException;
import com.example.demo.domain.model.Restaurante;
import com.example.demo.domain.repository.RestauranteRepository;
import com.example.demo.domain.service.CadastroRestauranteService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CadastroRestauranteService cadastroRestaurante;
	
	@GetMapping
	public List<Restaurante> listar(){
		return restauranteRepository.listar();
	} 
	
	@GetMapping("/{id}")
	public ResponseEntity<Restaurante> buscar(@PathVariable Long id) {
		Restaurante restaurante = restauranteRepository.buscar(id);
		if (restaurante != null) {
			return ResponseEntity.ok(restaurante);
		}
		return ResponseEntity.notFound().build();
	}
	
	
	
	@PostMapping
	public ResponseEntity<?> criar(@RequestBody Restaurante restaurante) {
		try {
			restaurante = cadastroRestaurante.salvar(restaurante);
			return ResponseEntity.status(HttpStatus.CREATED).body(restaurante);
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Restaurante> remover (@PathVariable Long id) {
		try {
			cadastroRestaurante.remover(id);
			return ResponseEntity.noContent().build();
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Restaurante restaurante){
		try {
			Restaurante restaurantebusca = restauranteRepository.buscar(id);
			if (restaurantebusca != null) {
				BeanUtils.copyProperties(restaurante, restaurantebusca, "id");
				cadastroRestaurante.salvar(restaurantebusca);
				return ResponseEntity.ok(restaurantebusca);
			}
			return ResponseEntity.notFound().build();	
		} catch (EntidadeNaoEncontradaException e ) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	// o map faz com que se tenha controle de todos os argumentos que se deseja atualizar 	
	@PatchMapping("/{id}")
	 public ResponseEntity<?> atualizarParcial(@PathVariable Long id, @RequestBody Map<String, Object> campos){
		  try {
			  Restaurante restaurante = restauranteRepository.buscar(id);
			  if (restaurante == null) {
				  return ResponseEntity.notFound().build();
			  }
			  merge(campos, restaurante);
			  cadastroRestaurante.salvar(restaurante);
			  return ResponseEntity.ok(restaurante);
		  } catch (EntidadeNaoEncontradaException e) {
			  return ResponseEntity.badRequest().build();
		  }
		}	 
	 
	private void merge(Map<String, Object> camposOrigem, Restaurante restauranteDestino) {
		ObjectMapper objectMapper = new ObjectMapper();
		Restaurante restauranteOrigem = objectMapper.convertValue(camposOrigem, Restaurante.class);
		camposOrigem.forEach((nomePropriedade, valorPropriedade) -> {
			Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
			//tornando acessiveo esse campo, por que o campo nome da classe restaurante é privado			
			field.setAccessible(true);
			System.out.println("");
			System.out.println("CAMPO:");
			System.out.println("");
			System.out.println(field);
			
			//pegando o campo do field, ex: "nome".				
			Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);
			System.out.println("");
			System.out.println("NOVO VALOR:");
			System.out.println("");
			System.out.println(novoValor);
			
			//estou setando no campo field de restauranteDestivo o valorPropriedade.  			
			ReflectionUtils.setField(field, restauranteDestino, novoValor);
 		});
	}
	
	
	
	
	
	
	
	
}
