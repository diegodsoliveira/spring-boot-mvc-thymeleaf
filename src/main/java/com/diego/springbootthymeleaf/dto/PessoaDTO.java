package com.diego.springbootthymeleaf.dto;

import java.io.Serializable;

import com.diego.springbootthymeleaf.model.Pessoa;

public class PessoaDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;
  private String nome;
  private String sobrenome;
  private String sexo;

  public Pessoa transformaPUsuario() {
    return new Pessoa(id, nome, sobrenome, sexo);
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNome() {
    return this.nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getSobrenome() {
    return this.sobrenome;
  }

  public void setSobrenome(String sobrenome) {
    this.sobrenome = sobrenome;
  }

  public String getSexo() {
    return this.sexo;
  }

  public void setSexo(String sexo) {
    this.sexo = sexo;
  }
}
