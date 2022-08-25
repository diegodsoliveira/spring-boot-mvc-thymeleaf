package com.diego.springbootthymeleaf.dto;

import java.io.Serializable;

public class TelefoneDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  private String numero;
  private String tipo;
  private PessoaDTO pessoaDTO;

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

  public PessoaDTO getPessoaDTO() {
    return this.pessoaDTO;
  }

  public void setPessoaDTO(PessoaDTO pessoaDTO) {
    this.pessoaDTO = pessoaDTO;
  }
}
