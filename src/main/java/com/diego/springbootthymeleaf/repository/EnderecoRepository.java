package com.diego.springbootthymeleaf.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.diego.springbootthymeleaf.model.Endereco;

@Repository
@Transactional
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

  @Query("select e from Endereco e where e.pessoa.id = ?1")
  List<Endereco> findAdressById(Long id);
  
}
