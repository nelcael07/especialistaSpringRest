package com.apirest.especialistaspringrest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.apirest.especialistaspringrest.di.modelo.Cliente;
import com.apirest.especialistaspringrest.di.service.AtivacaoClienteService;
import com.apirest.especialistaspringrest.di.service.Notificador;

@Controller
public class MeuPrimeiroController {
	
  private AtivacaoClienteService ativacaoClienteService;
  
  public MeuPrimeiroController(AtivacaoClienteService ativacaoClienteService) {
	this.ativacaoClienteService = ativacaoClienteService;
  }


  @GetMapping("/hello")
  @ResponseBody
  public String hello() {
	Cliente nelcael = new Cliente("Nelcael","nelcaif@gmail.com","989753545");
	ativacaoClienteService.ativar(nelcael);
    return "Hello word";
  }
  
}