package com.diego.springbootthymeleaf.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Role implements GrantedAuthority {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String nomeRole;

  @Override
  public String getAuthority() {
    return nomeRole;
  }

  public String getNomeRole() {
    return this.nomeRole;
  }

  public void setNomeRole(String nomeRole) {
    this.nomeRole = nomeRole;
  }

}
