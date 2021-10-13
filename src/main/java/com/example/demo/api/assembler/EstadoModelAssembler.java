package com.example.demo.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.api.model.EstadoModel;
import com.example.demo.domain.model.Estado;


@Component
public class EstadoModelAssembler {
	
	@Autowired 
	private ModelMapper modelMapper;
	
	public EstadoModel convertEstadoModel (Estado Estado) {
		return modelMapper.map(Estado, EstadoModel.class);
	}
	
	public List<EstadoModel> convertListEstadoModel(List<Estado> estados){
		return estados.stream()
				.map( estado -> convertEstadoModel(estado))
				.collect(Collectors.toList());
	}
	
}
