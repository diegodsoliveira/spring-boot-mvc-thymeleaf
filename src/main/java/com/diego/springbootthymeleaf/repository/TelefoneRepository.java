package com.diego.springbootthymeleaf.repository;

import com.diego.springbootthymeleaf.model.Telefone;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface TelefoneRepository extends CrudRepository<Telefone, Long> {

  @Query("select t from Telefone t where t.pessoa.id = ?1")
  List<Telefone> findTelefonesById(Long pessoaId);
}
