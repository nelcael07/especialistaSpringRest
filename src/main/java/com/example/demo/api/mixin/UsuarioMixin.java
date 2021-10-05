package com.example.demo.api.mixin;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class UsuarioMixin {
	
	@JsonIgnore
	private LocalDateTime dataCadastro;
	
}
