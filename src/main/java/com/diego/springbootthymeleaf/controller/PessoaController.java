package com.diego.springbootthymeleaf.controller;

import com.diego.springbootthymeleaf.dto.PessoaDTO;
import com.diego.springbootthymeleaf.model.Pessoa;
import com.diego.springbootthymeleaf.repository.PessoaRepository;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
  public ModelAndView salvarPessoa(@Valid PessoaDTO pessoaDto, BindingResult bindingResult) {
    ModelAndView modelAndView = new ModelAndView(CADASTRO_CADASTROPESSOA);
    
    if (bindingResult.hasErrors()) {
      
      modelAndView.addObject("pessoaobj", pessoaDto);
      
      List<String> msgErro = new ArrayList<>();
      
      for (ObjectError objectError : bindingResult.getAllErrors()) {
        msgErro.add(objectError.getDefaultMessage());
      }
      
      modelAndView.addObject("msgErro", msgErro);
      return modelAndView;
    }
    
    Pessoa pessoa = new Pessoa();
    
    pessoa.setId(pessoaDto.getId());
    pessoa.setNome(pessoaDto.getNome());
    pessoa.setSobrenome(pessoaDto.getSobrenome());
    pessoa.setTelefones(pessoaDto.getTelefones());
    pessoaRepository.save(pessoa);
    
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
    pessoaRepository.save(pessoa.get());

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
  public ModelAndView pesquisar(
    @RequestParam("nomepesquisa") String nomepesquisa
  ) {
    ModelAndView modelAndView = new ModelAndView(CADASTRO_CADASTROPESSOA);

    List<Pessoa> lPessoas = pessoaRepository.findPessoaByName(nomepesquisa);

    modelAndView.addObject("pessoas", lPessoas);
    modelAndView.addObject("pessoaobj", new Pessoa());

    return modelAndView;
  }
}
