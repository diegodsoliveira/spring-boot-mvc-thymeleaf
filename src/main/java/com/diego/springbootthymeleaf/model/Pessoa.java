package com.diego.springbootthymeleaf.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Pessoa implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(nullable = false)
  private String nome;

  @Column(nullable = false)
  private String sobrenome;

  @OneToMany(mappedBy = "pessoa", orphanRemoval = true, cascade = CascadeType.REMOVE)
  private List<Telefone> telefones;

  public List<Telefone> getTelefones() {
    return this.telefones;
  }

  public void setTelefones(List<Telefone> telefones) {
    this.telefones = telefones;
  }

  public Pessoa() {}

  public Pessoa(Long id, String nome, String sobrenome) {
    this.id = id;
    this.nome = nome;
    this.sobrenome = sobrenome;
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

  @Override
  public String toString() {
    return (
      "{" +
      " id='" +
      getId() +
      "'" +
      ", nome='" +
      getNome() +
      "'" +
      ", sobrenome='" +
      getSobrenome() +
      "'" +
      "}"
    );
  }
}
