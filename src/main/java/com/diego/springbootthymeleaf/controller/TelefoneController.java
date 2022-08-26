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

import com.diego.springbootthymeleaf.dto.TelefoneDTO;
import com.diego.springbootthymeleaf.model.Pessoa;
import com.diego.springbootthymeleaf.model.Telefone;
import com.diego.springbootthymeleaf.repository.PessoaRepository;
import com.diego.springbootthymeleaf.repository.TelefoneRepository;

@Controller
public class TelefoneController {

  private static final String CADASTRO_TELEFONES = "cadastro/telefones";

  @Autowired
  private PessoaRepository pessoaRepository;

  @Autowired
  private TelefoneRepository telefoneRepository;

  @GetMapping("/telefones/{idpessoa}")
  public ModelAndView telefones(@PathVariable("idpessoa") Long idpessoa) {
    ModelAndView modelAndView = new ModelAndView(CADASTRO_TELEFONES);

    Optional<Pessoa> optional = pessoaRepository.findById(idpessoa);

    if (optional.isPresent()) {

      modelAndView.addObject("pessoaobj", optional.get());
      modelAndView.addObject("telefones",
          telefoneRepository.findTelefonesById(idpessoa));
    }
    return modelAndView;
  }

  @PostMapping("**/addfonepessoa/{pessoaid}")
  public ModelAndView salvarTelefone(@Valid TelefoneDTO telefonetDto, @PathVariable("pessoaid") Long pessoaid,
      BindingResult bindingResult) {
    
    ModelAndView modelAndView = new ModelAndView(CADASTRO_TELEFONES);

    Optional<Pessoa> optional = pessoaRepository.findById(pessoaid);

    if (bindingResult.hasErrors()) {
      List<String> msgErro = new ArrayList<String>();

      for (ObjectError objectError : bindingResult.getAllErrors()) {
        msgErro.add(objectError.getDefaultMessage());
      }
      modelAndView.addObject("msgErro", msgErro);
      return modelAndView;
    }

    if (optional.isPresent()) {
      telefoneRepository.save(telefonetDto.transformaDtoParaTelefone());
      
      modelAndView.addObject("pessoaobj", optional.get());
      modelAndView.addObject("telefones", telefoneRepository.findTelefonesById(pessoaid));

    }
    return modelAndView;
  }

  @GetMapping("**/deletarfonepessoa/{telefoneid}")
  public ModelAndView deletarFonePessoa(@PathVariable("telefoneid") Long telefoneid) {
    ModelAndView modelAndView = new ModelAndView(CADASTRO_TELEFONES);
    
    Optional<Telefone> optional = telefoneRepository.findById(telefoneid);

    if (optional.isPresent()) {
      telefoneRepository.deleteById(telefoneid);
      
      modelAndView.addObject("pessoaobj", optional.get().getPessoa());
      modelAndView.addObject(
          "telefones",
          telefoneRepository.findTelefonesById(optional.get().getPessoa().getId()));
    }
    return modelAndView;
  }
}
