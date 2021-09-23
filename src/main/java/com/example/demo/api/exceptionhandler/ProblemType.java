package com.example.demo.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {
	ENTIDADE_NAO_ENCONTRADA("Entidade não encontrada", "/entidade-nao-encontrada"),
	ENTIDADE_INTERNA_NAO_ENCONTRADA("Entidade interna não encontrada" , "/entidade-interna-nao-encontrada"),
	ENTIDADE_EM_USO("Entidade em uso","/entidade-em-uso");
	
	private String title;
	
	private String uri;
	
	ProblemType(String title, String path){
		this.uri = "https://restaapinelca.com.br" + path;
		this.title = title;
	}
}
