package com.diego.springbootthymeleaf.dto;

import java.io.Serializable;

import com.diego.springbootthymeleaf.model.Pessoa;
import com.diego.springbootthymeleaf.model.Telefone;

public class TelefoneDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  private String numero;
  private String tipo;
  private Pessoa pessoa;

  public Telefone transformaDtoParaTelefone() {
    return new Telefone(numero, tipo, pessoa);
  }

  public String getNumero() {
    return this.numero;
  }

  public void setNumero(String numero) {
    this.numero = numero;
  }

  public String getTipo() {
    return this.tipo;
  }

  public void setTipo(String tipo) {
    this.tipo = tipo;
  }

  public Pessoa getPessoa() {
    return this.pessoa;
  }

  public void setPessoa(Pessoa pessoa) {
    this.pessoa = pessoa;
  }
}
