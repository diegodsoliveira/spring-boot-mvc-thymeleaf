package com.diego.springbootthymeleaf.repository;

import com.diego.springbootthymeleaf.model.Pessoa;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * PessoaRepository
 */
@Repository
@Transactional
public interface PessoaRepository extends CrudRepository<Pessoa, Long> {}
