package com.example.demo.core.validation;

import java.math.BigDecimal;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MultiploValidator implements ConstraintValidator<Multiplo, Number>{
	
	private int numeroMultiplo;
	
	//metodo para preparar para a validação, ele pega todos os dados da instancia da anotação que será usado.	
	@Override
	public void initialize(Multiplo constraintAnnotation) {
		this.numeroMultiplo = constraintAnnotation.numero();
	}
	
	@Override
	public boolean isValid(Number value, ConstraintValidatorContext context) {
		boolean valido = true;
		
		if (value != null) {
			var valorDecimal = BigDecimal.valueOf(value.doubleValue());
			var multiploDecimal = BigDecimal.valueOf(this.numeroMultiplo);
			//remainder pega o resto da divisão de um elemento pelo outro.				
			var resto = valorDecimal.remainder(multiploDecimal);
			//se o compare to retornar diferente de 0 será false			
			valido = BigDecimal.ZERO.compareTo(resto) == 0;	
		}
		
		return valido;
	}
	
}
