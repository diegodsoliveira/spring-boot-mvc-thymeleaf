package com.diego.springbootthymeleaf.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.diego.springbootthymeleaf.dto.TelefoneDTO;
import com.diego.springbootthymeleaf.model.Pessoa;
import com.diego.springbootthymeleaf.model.Telefone;
import com.diego.springbootthymeleaf.repository.PessoaRepository;
import com.diego.springbootthymeleaf.repository.TelefoneRepository;

@Controller
public class TelefoneController {

  @Autowired
  private PessoaRepository pessoaRepository;

  @Autowired
  private TelefoneRepository telefoneRepository;

  @GetMapping("/telefones/{idpessoa}")
  public ModelAndView telefones(@PathVariable("idpessoa") Long idpessoa) {
    ModelAndView modelAndView = new ModelAndView("cadastro/telefones");

    Pessoa pessoa = pessoaRepository.findById(idpessoa).get();

    modelAndView.addObject("pessoaobj", pessoa);
    modelAndView.addObject(
      "telefones",
      telefoneRepository.findTelefonesById(idpessoa)
    );

    return modelAndView;
  }

  @PostMapping("**/addfonepessoa/{pessoaid}")
  public ModelAndView addTelefone(@Valid TelefoneDTO telefonetDto, @PathVariable("pessoaid") Long pessoaid, BindingResult bindingResult) {
    ModelAndView modelAndView = new ModelAndView("cadastro/telefones");
    Pessoa pessoa = pessoaRepository.findById(pessoaid).get();
    modelAndView.addObject("pessoaobj", pessoa);
    
    if (bindingResult.hasErrors()) {
      List<String> msgErro = new ArrayList<>();
      
      for (ObjectError objectError : bindingResult.getAllErrors()) {
        msgErro.add(objectError.getDefaultMessage());
      }
      modelAndView.addObject("msgErro", msgErro);
      return modelAndView;
    }
    
    Telefone telefone = new Telefone();
    
    telefone.setPessoa(pessoa);
    telefone.setNumero(telefonetDto.getNumero());
    telefone.setTipo(telefonetDto.getTipo());
    telefoneRepository.save(telefone);
    
    modelAndView.addObject("telefones", telefoneRepository.findTelefonesById(pessoa.getId()));
    return modelAndView;
  }

  @GetMapping("**/deletarfonepessoa/{telefoneid}")
  public ModelAndView deletarFonePessoa(@PathVariable("telefoneid") Long telefoneid) {
    Pessoa pessoa = telefoneRepository.findById(telefoneid).get().getPessoa();

    telefoneRepository.deleteById(telefoneid);

    ModelAndView modelAndView = new ModelAndView("cadastro/telefones");

    modelAndView.addObject("pessoaobj", pessoa);
    modelAndView.addObject(
      "telefones",
      telefoneRepository.findTelefonesById(pessoa.getId())
    );

    return modelAndView;
  }
}
