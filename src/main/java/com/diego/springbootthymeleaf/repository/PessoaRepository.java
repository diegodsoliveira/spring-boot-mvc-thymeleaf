package com.diego.springbootthymeleaf.repository;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.diego.springbootthymeleaf.model.Pessoa;

@Repository
@Transactional
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
  @Query("select p from Pessoa p where p.nome like %?1%")
  List<Pessoa> findPessoaByName(String nome);

  @Query("select p from Pessoa p where p.nome like %?1% and p.sexo = ?2")
  List<Pessoa> findPessoaByNameAndSexo(String nomepesquisa, String pesquisaSexo);

  @Query("select p from Pessoa p where p.sexo = ?1")
  List<Pessoa> findPessoaBySexo(String pesquisaSexo);

  default Page<Pessoa> findPessoaByNamePage(String nome, Pageable pageable) {
    Pessoa pessoa = new Pessoa();
    pessoa.setNome(nome);

    ExampleMatcher exampleMatcher = ExampleMatcher.matchingAny().withMatcher("nome",
        ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());

    Example<Pessoa> example = Example.of(pessoa, exampleMatcher);

    return findAll(example, pageable);
  }

  default Page<Pessoa> findPessoaByNameAndSexoPage(String nome, String sexo, Pageable pageable) {
    
    Pessoa pessoa = new Pessoa();
    pessoa.setNome(nome);
    pessoa.setSexo(sexo);

    ExampleMatcher exampleMatcher = ExampleMatcher.matchingAny()
        .withMatcher("nome", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
        .withMatcher("sexo", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());

    Example<Pessoa> example = Example.of(pessoa, exampleMatcher);

    Page<Pessoa> page = findAll(example, pageable);

    return page;
  }
}
