package com.example.demo.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {
	MENSAGEM_INCOMPREENSIVEL("Mensagem Inconpreensivel","/mensagem-inconpreensivel"),
	RECURSO_NAO_ENCONTRADA("Recurso não encontrado", "/recurso-nao-encontrado"),
	ENTIDADE_INTERNA_NAO_ENCONTRADA("Entidade interna não encontrada" , "/entidade-interna-nao-encontrada"),
	ENTIDADE_EM_USO("Entidade em uso","/entidade-em-uso"),
	ENTIDADE_IGNORADA("Entidade está ignorada ", "/entidade-ignorada"),
	ENTIDADE_NAO_EXISTE("Entidade não existe", "/entidade-nao-existe"),
	PARAMETRO_INVALIDO("Parametro invalido", "/parametro-invalido"),
	ERRO_NO_SISTEMA("Erro no sistema", "/erro-no-sistema");
	
	private String title;
	
	private String uri;
	
	ProblemType(String title, String path){
		this.uri = "https://restaapinelca.com.br" + path;
		this.title = title;
	}
}
