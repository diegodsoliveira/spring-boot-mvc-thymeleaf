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
import javax.validation.constraints.NotBlank;

@Entity
public class Pessoa implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(nullable = false)
  @NotBlank(message = "O nome é obrigatório.")
  private String nome;

  @NotBlank(message = "O sobrenome é obrigatório.")
  @Column(nullable = false)
  private String sobrenome;

  @OneToMany(mappedBy = "pessoa", orphanRemoval = true, cascade = CascadeType.ALL)
  private List<Telefone> telefones;

  @OneToMany(mappedBy = "pessoa", orphanRemoval = true, cascade = CascadeType.ALL)
  private List<Endereco> enderecos;

  private String sexo;

  public Pessoa() {

  }

  public Pessoa(String nome, String sobrenome, List<Telefone> telefones, List<Endereco> enderecos, String sexo) {
    this.nome = nome;
    this.sobrenome = sobrenome;
    this.telefones = telefones;
    this.enderecos = enderecos;
    this.sexo = sexo;
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
