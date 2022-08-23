package com.diego.springbootthymeleaf.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.diego.springbootthymeleaf.model.Pessoa;
import com.diego.springbootthymeleaf.repository.PessoaRepository;

@Controller
public class PessoaController {

  private static final String CADASTRO_CADASTROPESSOA =
    "cadastro/cadastropessoa";

  @Autowired
  private PessoaRepository pessoaRepository;

  @GetMapping("/cadastropessoa")
  public ModelAndView inicio() {
    ModelAndView modelAndView = new ModelAndView(CADASTRO_CADASTROPESSOA);
    modelAndView.addObject("pessoas", pessoaRepository.findAll());
    modelAndView.addObject("pessoaobj", new Pessoa());

    return modelAndView;
  }

  @PostMapping("**/salvarpessoa")
  public ModelAndView salvarPessoa(Pessoa pessoa) {
    pessoaRepository.save(pessoa);

    ModelAndView modelAndView = new ModelAndView(CADASTRO_CADASTROPESSOA);

    Iterable<Pessoa> peIterable = pessoaRepository.findAll();

    modelAndView.addObject("pessoas", peIterable);
    modelAndView.addObject("pessoaobj", new Pessoa());

    return modelAndView;
  }

  @GetMapping("**/listarpessoas")
  public ModelAndView listarPessoas() {
    ModelAndView modelAndView = new ModelAndView(CADASTRO_CADASTROPESSOA);

    Iterable<Pessoa> peIterable = pessoaRepository.findAll();

    modelAndView.addObject("pessoas", peIterable);
    modelAndView.addObject("pessoaobj", new Pessoa());

    return modelAndView;
  }

  @GetMapping("**/editarpessoa/{idpessoa}")
  public ModelAndView editarPessoa(@PathVariable("idpessoa") Long idpessoa) {
    ModelAndView modelAndView = new ModelAndView(CADASTRO_CADASTROPESSOA);

    Optional<Pessoa> pessoa = pessoaRepository.findById(idpessoa);

    modelAndView.addObject("pessoaobj", pessoa.get());

    return modelAndView;
  }

  @GetMapping("**/excluirpessoa/{idpessoa}")
  public ModelAndView excluirPessoa(@PathVariable("idpessoa") Long idpessoa) {
    pessoaRepository.deleteById(idpessoa);

    ModelAndView modelAndView = new ModelAndView(CADASTRO_CADASTROPESSOA);
    modelAndView.addObject("pessoas", pessoaRepository.findAll());
    modelAndView.addObject("pessoaobj", new Pessoa());

    return modelAndView;
  }

  @PostMapping("**/pesquisarpessoa")
  public ModelAndView pesquisar(@RequestParam("nomepesquisa") String nomepesquisa) {
    ModelAndView modelAndView = new ModelAndView(CADASTRO_CADASTROPESSOA);

    List<Pessoa> lPessoas = pessoaRepository.findPessoaByName(nomepesquisa);

    modelAndView.addObject("pessoas", lPessoas);
    modelAndView.addObject("pessoaobj", new Pessoa());

    return modelAndView;
  }
}
