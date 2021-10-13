package com.example.demo.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.api.model.CidadeModel;
import com.example.demo.api.model.RestauranteModel;
import com.example.demo.domain.model.Cidade;
import com.example.demo.domain.model.Restaurante;

//CONVERÇÃO DE CIDADE PARA CIDADE MODEL

@Component
public class CidadeModelAssembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public CidadeModel toModel(Cidade cidade) {
		return modelMapper.map(cidade, CidadeModel.class);
	}
	
	public List<CidadeModel> toList(List<Cidade> cidades){
		return cidades.stream().map(cidade -> toModel(cidade))
			.collect(Collectors.toList());
	}
	
}






