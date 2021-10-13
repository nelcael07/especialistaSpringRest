package com.example.demo.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.api.model.input.CidadeInput;
import com.example.demo.domain.model.Cidade;
import com.example.demo.domain.model.Estado;

@Component
public class CidadeModelDiassembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Cidade toDomainObject (CidadeInput cidadeInput) {
		return modelMapper.map(cidadeInput, Cidade.class);
	}
	
	public void copyDomainObject (CidadeInput cidadeInput, Cidade cidade) {
		cidade.setEstado(new Estado());
		
		modelMapper.map(cidadeInput, cidade);
	}
	
}
