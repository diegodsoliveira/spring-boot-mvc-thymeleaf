package com.diego.springbootthymeleaf.controller;

import com.diego.springbootthymeleaf.model.Pessoa;
import com.diego.springbootthymeleaf.repository.PessoaRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PessoaController {

  @Autowired
  private PessoaRepository pessoaRepository;

  @RequestMapping(method = RequestMethod.GET, value = "/cadastropessoa")
  public ModelAndView inicio() {
    ModelAndView modelAndView = new ModelAndView("cadastro/cadastropessoa");
    modelAndView.addObject("pessoaobj", new Pessoa());

    return modelAndView;
  }

  @RequestMapping(method = RequestMethod.POST, value = "**/salvarpessoa")
  public ModelAndView salvarPessoa(Pessoa pessoa) {
    pessoaRepository.save(pessoa);

    ModelAndView modelAndView = new ModelAndView("cadastro/cadastropessoa");

    Iterable<Pessoa> peIterable = pessoaRepository.findAll();

    modelAndView.addObject("pessoas", peIterable);
    modelAndView.addObject("pessoaobj", new Pessoa());

    return modelAndView;
  }

  @RequestMapping(method = RequestMethod.GET, value = "/listarpessoas")
  public ModelAndView listarPessoas() {
    ModelAndView modelAndView = new ModelAndView("cadastro/cadastropessoa");

    Iterable<Pessoa> peIterable = pessoaRepository.findAll();

    modelAndView.addObject("pessoas", peIterable);
    modelAndView.addObject("pessoaobj", new Pessoa());

    return modelAndView;
  }

  @GetMapping("/editarpessoa/{idpessoa}")
  public ModelAndView editarPessoa(@PathVariable("idpessoa") Long idpessoa) {
    ModelAndView modelAndView = new ModelAndView("cadastro/cadastropessoa");

    Optional<Pessoa> pessoa = pessoaRepository.findById(idpessoa);

    modelAndView.addObject("pessoaobj", pessoa.get());

    return modelAndView;
  }

  @GetMapping("/excluirpessoa/{idpessoa}")
  public ModelAndView excluirPessoa(@PathVariable("idpessoa") Long idpessoa) {
    ModelAndView modelAndView = new ModelAndView("cadastro/cadastropessoa");

    pessoaRepository.deleteById(idpessoa);
    modelAndView.addObject("pessoaobj", new Pessoa());

    return modelAndView;
  }
}
