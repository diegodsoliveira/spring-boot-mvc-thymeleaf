package com.diego.springbootthymeleaf.dto;

import java.io.Serializable;

import com.diego.springbootthymeleaf.model.Endereco;
import com.diego.springbootthymeleaf.model.Pessoa;

public class EnderecoDTO implements Serializable {
  private static final long serialVersionUID = 1L;

  private Long id;
  private String cep;
  private String rua;
  private String bairro;
  private String cidade;
  private String numero;
  private String uf;
  private Pessoa pessoa;

  public Endereco transformaDtoParaObjeto() {
    return new Endereco(id, cep, rua, bairro, cidade, uf, numero, pessoa);
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Pessoa getPessoa() {
    return this.pessoa;
  }

  public void setPessoa(Pessoa pessoa) {
    this.pessoa = pessoa;
  }

  public String getCep() {
    return this.cep;
  }

  public void setCep(String cep) {
    this.cep = cep;
  }

  public String getRua() {
    return this.rua;
  }

  public void setRua(String rua) {
    this.rua = rua;
  }

  public String getBairro() {
    return this.bairro;
  }

  public void setBairro(String bairro) {
    this.bairro = bairro;
  }

  public String getCidade() {
    return this.cidade;
  }

  public void setCidade(String cidade) {
    this.cidade = cidade;
  }

  public String getNumero() {
    return this.numero;
  }

  public void setNumero(String numero) {
    this.numero = numero;
  }

  public String getUf() {
    return this.uf;
  }

  public void setUf(String uf) {
    this.uf = uf;
  }


}
