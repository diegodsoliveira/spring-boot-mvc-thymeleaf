package com.diego.springbootthymeleaf.dto;

import java.io.Serializable;
import java.util.List;

import com.diego.springbootthymeleaf.model.Endereco;
import com.diego.springbootthymeleaf.model.Pessoa;
import com.diego.springbootthymeleaf.model.Telefone;

public class PessoaDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  private String nome;
  private String sobrenome;
  private List<Telefone> telefones;
  private List<Endereco> enderecos;
  private String sexo;

  public Pessoa transformaPUsuario() {
    return new Pessoa(nome, sobrenome, telefones, enderecos, sexo);
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

  public List<Telefone> getTelefones() {
    return this.telefones;
  }

  public void setTelefones(List<Telefone> telefones) {
    this.telefones = telefones;
  }

  public List<Endereco> getEnderecos() {
    return this.enderecos;
  }

  public void setEnderecos(List<Endereco> enderecos) {
    this.enderecos = enderecos;
  }

  public String getSexo() {
    return this.sexo;
  }

  public void setSexo(String sexo) {
    this.sexo = sexo;
  }
}
