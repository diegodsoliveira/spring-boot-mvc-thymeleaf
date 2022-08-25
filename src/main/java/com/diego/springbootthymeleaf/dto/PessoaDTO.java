package com.diego.springbootthymeleaf.dto;

import com.diego.springbootthymeleaf.model.Telefone;
import java.io.Serializable;
import java.util.List;

public class PessoaDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;
  private String nome;
  private String sobrenome;
  private List<Telefone> telefones;

  public List<Telefone> getTelefones() {
    return this.telefones;
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setTelefones(List<Telefone> telefones) {
    this.telefones = telefones;
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
}
