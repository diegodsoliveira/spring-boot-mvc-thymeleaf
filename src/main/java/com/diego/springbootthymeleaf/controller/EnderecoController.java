package com.diego.springbootthymeleaf.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.diego.springbootthymeleaf.model.Endereco;
import com.diego.springbootthymeleaf.model.Pessoa;
import com.diego.springbootthymeleaf.repository.EnderecoRepository;
import com.diego.springbootthymeleaf.repository.PessoaRepository;

@Controller
public class EnderecoController {
  @Autowired
  private PessoaRepository pessoaRepository;

  @Autowired
  private EnderecoRepository enderecoRepository;

  @GetMapping("endereco/{idpessoa}")
  public ModelAndView telefones(@PathVariable("idpessoa") Long idpessoa) {
    ModelAndView modelAndView = new ModelAndView("cadastro/endereco");

    Optional<Pessoa> optional = pessoaRepository.findById(idpessoa);

    if (optional.isPresent()) {

      modelAndView.addObject("pessoaobj", optional.get());
      modelAndView.addObject("enderecos",
          enderecoRepository.findAdressById(idpessoa));
    }
    return modelAndView;
  }

  @PostMapping("**/salvarendereco/{pessoaid}")
  public ModelAndView salvarEndereco(@Valid Endereco endereco, @PathVariable("pessoaid") Long pessoaid,
      BindingResult bindingResult) {

    ModelAndView modelAndView = new ModelAndView("cadastro/endereco");

    Optional<Pessoa> optional = pessoaRepository.findById(pessoaid);

    if (bindingResult.hasErrors()) {
      List<String> msgErro = new ArrayList<>();

      for (ObjectError objectError : bindingResult.getAllErrors()) {
        msgErro.add(objectError.getDefaultMessage());
      }
      modelAndView.addObject("msgErro", msgErro);
      return modelAndView;
    }

    if (optional.isPresent()) {
      enderecoRepository.save(endereco);

      modelAndView.addObject("pessoaobj", optional.get());
      modelAndView.addObject("enderecos", enderecoRepository.findAdressById(pessoaid));

    }
    return modelAndView;
  }

  @GetMapping("**/deletarendereco/{enderecoid}")
  public ModelAndView deletarFonePessoa(@PathVariable("enderecoid") Long enderecoid) {
    ModelAndView modelAndView = new ModelAndView("cadastro/endereco");

    Optional<Endereco> optional = enderecoRepository.findById(enderecoid);

    if (optional.isPresent()) {
      enderecoRepository.deleteById(enderecoid);

      modelAndView.addObject("pessoaobj", optional.get().getPessoa());
      modelAndView.addObject(
          "enderecos",
          enderecoRepository.findAdressById(optional.get().getPessoa().getId()));
    }
    return modelAndView;
  }
}
