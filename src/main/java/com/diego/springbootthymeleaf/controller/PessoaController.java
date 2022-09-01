package com.diego.springbootthymeleaf.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.diego.springbootthymeleaf.model.Pessoa;
import com.diego.springbootthymeleaf.repository.EnderecoRepository;
import com.diego.springbootthymeleaf.repository.PessoaRepository;
import com.diego.springbootthymeleaf.repository.ProfissaoRepository;
import com.diego.springbootthymeleaf.repository.TelefoneRepository;

@Controller
public class PessoaController {

  private static final String CADASTROPESSOA = "cadastro/cadastropessoa";

  @Autowired
  private PessoaRepository pessoaRepository;

  @Autowired
  private ProfissaoRepository profissaoRepository;

  @Autowired
  private TelefoneRepository telefoneRepository;

  @Autowired
  private EnderecoRepository enderecoRepository;

  @Autowired
  private ReportUtil reportUtil;

  @GetMapping("/cadastropessoa")
  public ModelAndView inicio() {
    ModelAndView modelAndView = new ModelAndView(CADASTROPESSOA);
    modelAndView.addObject("pessoas", pessoaRepository.findAll(PageRequest.of(0, 5, Sort.by("nome"))));
    modelAndView.addObject("pessoaobj", new Pessoa());
    modelAndView.addObject("profissoes", profissaoRepository.findAll());

    return modelAndView;
  }

  @GetMapping("**/pessoaspag")
  public ModelAndView carregaPessoaPorPaginacao(@PageableDefault(size = 5) Pageable pageable,
      ModelAndView modelAndView, @RequestParam("nomepesquisa") String nomepesquisa) {
   modelAndView.addObject("pessoas", pessoaRepository.findPessoaByNamePage(nomepesquisa, pageable));
   modelAndView.addObject("pessoaobj", new Pessoa());
   modelAndView.addObject("nomepesquisa", nomepesquisa);
   modelAndView.addObject("profissoes", profissaoRepository.findAll());
   modelAndView.setViewName(CADASTROPESSOA);

   return modelAndView;
  }

  @PostMapping(value = "**/salvarpessoa", consumes = { "multipart/form-data" })
  public ModelAndView salvarPessoa(@Valid Pessoa pessoa, BindingResult bindingResult, final MultipartFile file)
      throws IOException {
    ModelAndView modelAndView = new ModelAndView(CADASTROPESSOA);

    if (bindingResult.hasErrors()) {

      modelAndView.addObject("pessoaobj", pessoa);

      List<String> msgErro = new ArrayList<>();

      for (ObjectError objectError : bindingResult.getAllErrors()) {
        msgErro.add(objectError.getDefaultMessage());
      }

      modelAndView.addObject("msgErro", msgErro);
      return modelAndView;
    }

    pessoa.setTelefones(telefoneRepository.findTelefonesById(pessoa.getId()));
    pessoa.setEnderecos(enderecoRepository.findAdressById(pessoa.getId()));

    if (file.getSize() > 0) {
      pessoa.setCurriculo(file.getBytes());
      pessoa.setNomeFileCurriculo(file.getOriginalFilename());
      pessoa.setTipoFileCurriculo(file.getContentType());
    } else {
      if (pessoa.getId() != null && pessoa.getId() > 0) {
        Optional<Pessoa> optional = pessoaRepository.findById(pessoa.getId());

        if (optional.isPresent()) {
          pessoa.setCurriculo(optional.get().getCurriculo());
          pessoa.setNomeFileCurriculo(optional.get().getNomeFileCurriculo());
          pessoa.setTipoFileCurriculo(optional.get().getTipoFileCurriculo());
        }
      }
    }
    pessoaRepository.save(pessoa);

    modelAndView.addObject("pessoas", pessoaRepository.findAll(PageRequest.of(0, 5, Sort.by("nome"))));
    modelAndView.addObject("pessoaobj", new Pessoa());
    modelAndView.addObject("profissoes", profissaoRepository.findAll());
    return modelAndView;
  }

  @GetMapping("**/listarpessoas")
  public ModelAndView listarPessoas() {
    ModelAndView modelAndView = new ModelAndView(CADASTROPESSOA);

    modelAndView.addObject("pessoas", pessoaRepository.findAll(PageRequest.of(0, 5, Sort.by("nome"))));
    modelAndView.addObject("pessoaobj", new Pessoa());
    modelAndView.addObject("profissoes", profissaoRepository.findAll());

    return modelAndView;
  }

  @GetMapping("**/editarpessoa/{idpessoa}")
  public ModelAndView editarPessoa(@PathVariable("idpessoa") Long idpessoa) {
    ModelAndView modelAndView = new ModelAndView(CADASTROPESSOA);

    Optional<Pessoa> pessoa = pessoaRepository.findById(idpessoa);

    if (pessoa.isPresent()) {
      modelAndView.addObject("pessoaobj", pessoa.get());
      modelAndView.addObject("profissoes", profissaoRepository.findAll());
    }

    return modelAndView;
  }

  @GetMapping("**/excluirpessoa/{idpessoa}")
  public ModelAndView excluirPessoa(@PathVariable("idpessoa") Long idpessoa) {
    pessoaRepository.deleteById(idpessoa);

    ModelAndView modelAndView = new ModelAndView(CADASTROPESSOA);
    modelAndView.addObject("pessoas", pessoaRepository.findAll(PageRequest.of(0, 5, Sort.by("nome"))));
    modelAndView.addObject("pessoaobj", new Pessoa());
    modelAndView.addObject("profissoes", profissaoRepository.findAll());

    return modelAndView;
  }

  @PostMapping("**/pesquisarpessoa")
  public ModelAndView pesquisar(
      @RequestParam("nomepesquisa") String nomepesquisa,
      @RequestParam("pesquisaSexo") String pesquisaSexo,
      @PageableDefault(size = 5, sort = {"nome"}) Pageable pageable) {
    ModelAndView modelAndView = new ModelAndView(CADASTROPESSOA);

    if (pesquisaSexo != null && !pesquisaSexo.isEmpty()) {
      modelAndView.addObject("pessoas",
          pessoaRepository.findPessoaByNameAndSexoPage(nomepesquisa, pesquisaSexo, pageable));
    } else {
      modelAndView.addObject("pessoas",
          pessoaRepository.findPessoaByNamePage(nomepesquisa, pageable));
    }

    modelAndView.addObject("pessoaobj", new Pessoa());
    modelAndView.addObject("nomepesquisa", nomepesquisa);
    modelAndView.addObject("profissoes", profissaoRepository.findAll());

    return modelAndView;
  }

  @GetMapping("**/pesquisarpessoa")
  public void imprimePdf(
      @RequestParam("nomepesquisa") String nomepesquisa,
      @RequestParam("pesquisaSexo") String pesquisaSexo,
      HttpServletRequest request, HttpServletResponse response) throws Exception {

    List<Pessoa> pessoas = new ArrayList<>();

    if (pesquisaSexo != null && !pesquisaSexo.isEmpty()
        && nomepesquisa != null && !nomepesquisa.isEmpty()) {

      pessoas = pessoaRepository.findPessoaByNameAndSexo(nomepesquisa, pesquisaSexo);

    } else if (nomepesquisa != null && !nomepesquisa.isEmpty()) {

      pessoas = pessoaRepository.findPessoaByName(nomepesquisa);

    } else if (pesquisaSexo != null && !pesquisaSexo.isEmpty()) {

      pessoas = pessoaRepository.findPessoaBySexo(pesquisaSexo);

    } else {

      Iterable<Pessoa> iterator = pessoaRepository.findAll();

      for (Pessoa pessoa : iterator) {
        pessoas.add(pessoa);
      }
    }

    byte[] pdf = reportUtil.geraRelatorio(pessoas, "spring-boot-thymeleaf", request.getServletContext());

    response.setContentLength(pdf.length);

    response.setContentType("application/octet-stream");

    String headerKey = "Content-Disposition";
    String headerValue = String.format("attachment; filename=\"%s\"", "relatorio-pessoas.pdf");

    response.setHeader(headerKey, headerValue);

    response.getOutputStream().write(pdf);

  }

  @GetMapping("**/baixarcurriculo/{idpessoa}")
  public void baixarCurriculo(@PathVariable("idpessoa") Long idpessoa, HttpServletResponse response)
      throws IOException {
    Optional<Pessoa> optional = pessoaRepository.findById(idpessoa);

    if (optional.isPresent()) {

      if (optional.get().getCurriculo() != null) {
        response.setContentLength(optional.get().getCurriculo().length);

        /* generic = application/octet-stream */
        response.setContentType(optional.get().getTipoFileCurriculo());

        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", optional.get().getNomeFileCurriculo());

        response.setHeader(headerKey, headerValue);

        response.getOutputStream().write(optional.get().getCurriculo());
      }
    }
  }
}
