package com.diego.springbootthymeleaf.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ForeignKey;

@Entity
public class Endereco implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(nullable = false)
  private String cep;

  @Column(nullable = false)
  private String rua;

  @Column(nullable = false)
  private String bairro;

  @Column(nullable = false)
  private String cidade;

  @Column(nullable = false)
  private String uf;

  @Column(nullable = false)
  private String numero;

  @ManyToOne
  @ForeignKey(name = "pessoa_id")
  private Pessoa pessoa;

  public Endereco(Long id, String cep, String rua, String bairro, String cidade, String uf, String numero, Pessoa pessoa) {
    this.id = id;
    this.cep = cep;
    this.rua = rua;
    this.bairro = bairro;
    this.cidade = cidade;
    this.uf = uf;
    this.numero = numero;
    this.pessoa = pessoa;
  }

  public Endereco() {
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public String getUf() {
    return this.uf;
  }

  public void setUf(String uf) {
    this.uf = uf;
  }

  public String getNumero() {
    return this.numero;
  }

  public void setNumero(String numero) {
    this.numero = numero;
  }

  public Pessoa getPessoa() {
    return this.pessoa;
  }

  public void setPessoa(Pessoa pessoa) {
    this.pessoa = pessoa;
  }

  @Override
  public String toString() {
    return "{" +
        " id='" + getId() + "'" +
        ", cep='" + getCep() + "'" +
        ", rua='" + getRua() + "'" +
        ", bairro='" + getBairro() + "'" +
        ", cidade='" + getCidade() + "'" +
        ", uf='" + getUf() + "'" +
        ", numero='" + getNumero() + "'" +
        ", pessoa='" + getPessoa() + "'" +
        "}";
  }

}
