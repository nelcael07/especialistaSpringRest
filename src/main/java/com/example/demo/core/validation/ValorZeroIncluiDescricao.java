package com.example.demo.core.validation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

//vai ser para ser utilizada em class, inteface, enuns
@Target({ ElementType.TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = { ValorZeroIncluiDescricaoValidator.class })
public @interface ValorZeroIncluiDescricao {
	
	String message() default "Não contém a descricao 'Frete Grátis'.";
	
	Class<?>[] groups() default { };
	
	
	Class<? extends Payload>[] payload() default { };
	
	String descricaoField();
	
	String descricaoObrigatoria();
	
	String valorField();
	
	
	
	
	
}

