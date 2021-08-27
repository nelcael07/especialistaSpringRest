package com.apirest.especilistaspringrest.di.notificacao;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.springframework.beans.factory.annotation.Qualifier;

//retention diz que essa anotação pode ser usada em tempo de execução 
@Retention(RetentionPolicy.RUNTIME)
@Qualifier
public @interface TipoDoNotificador {
	//somente passar NivelUrgencia antes do value ele já puxa um enun com esse nome
	NivelUrgencia value();
}
