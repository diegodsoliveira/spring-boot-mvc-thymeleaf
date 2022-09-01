package com.diego.springbootthymeleaf.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.diego.springbootthymeleaf.model.Usuario;

@Repository
@Transactional
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

  @Query("select u from Usuario u where trim(u.login) = ?1")
  Usuario findUserByLogin(String login);
}
