package com.diego.springbootthymeleaf.repository;

import com.diego.springbootthymeleaf.model.Pessoa;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface PessoaRepository extends CrudRepository<Pessoa, Long> {
  @Query("select p from Pessoa p where p.nome like %?1%")
  List<Pessoa> findPessoaByName(String nome);

  @Query("select p from Pessoa p where p.nome like %?1% and p.sexo = ?2")
  List<Pessoa> findPessoaByNameAndSexo(String nomepesquisa, String pesquisaSexo);

  @Query("select p from Pessoa p where p.sexo = ?1")
  List<Pessoa> findPessoaBySexo(String pesquisaSexo);
}
