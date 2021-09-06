package com.example.demo.domain.repository;

import java.util.List;
import com.example.demo.domain.model.Restaurante;

public interface RestauranteRepository {
	List<Restaurante> listar();
	Restaurante salvar(Restaurante restaurante);
	Restaurante buscar(Long id);
	void remover(Long id);
}
