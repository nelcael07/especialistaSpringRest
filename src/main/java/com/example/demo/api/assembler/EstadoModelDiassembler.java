package com.example.demo.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.api.model.input.EstadoInput;
import com.example.demo.domain.model.Estado;

@Component
public class EstadoModelDiassembler {
	
	@Autowired 
	private ModelMapper modelMapper;
	
	public Estado convertEstado (EstadoInput estadoInput) {
		return modelMapper.map(estadoInput, Estado.class);
	}
	
	public void updateBeanUtils (EstadoInput estadoInput, Estado estado) {
		modelMapper.map(estadoInput, estado);
	}
}
