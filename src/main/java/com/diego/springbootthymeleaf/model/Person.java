package com.diego.springbootthymeleaf.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Person implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String lastName;

  public Person() {
  }

  public Person(Long id, String name, String lastName) {
    this.id = id;
    this.name = name;
    this.lastName = lastName;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getlastName() {
    return this.lastName;
  }

  public void setlastName(String lastName) {
    this.lastName = lastName;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof Person)) {
      return false;
    }
    Person person = (Person) o;
    return Objects.equals(id, person.id) && Objects.equals(name, person.name)
        && Objects.equals(lastName, person.lastName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, lastName);
  }

  @Override
  public String toString() {
    return "{" +
        ", name='" + getName() + "'" +
        ", lastName='" + getlastName() + "'" +
        "}";
  }

}
