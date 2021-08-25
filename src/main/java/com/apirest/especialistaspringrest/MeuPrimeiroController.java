package com.apirest.especialistaspringrest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

// anotação do spring que disk que essa classe é um controller, ou seja, ela é responsavel por receber requisições http
@Controller
public class MeuPrimeiroController {
  // getMapping é uma anotação para especificar o caminho da rota dessa class hello
  @GetMapping("/hello")
  // anotação para dizer que essa url vai ter uma resposta
  @ResponseBody
  public String hello() {
    return "Hello Word";
  }
  
}