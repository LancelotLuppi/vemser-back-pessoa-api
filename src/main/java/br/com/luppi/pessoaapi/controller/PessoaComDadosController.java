package br.com.luppi.pessoaapi.controller;

import br.com.luppi.pessoaapi.dto.PessoaComDadosDTO;
import br.com.luppi.pessoaapi.exception.EntidadeNaoEncontradaException;
import br.com.luppi.pessoaapi.service.PessoaComDadosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pessoa-com-dados")
@Validated
public class PessoaComDadosController {

    @Autowired
    private PessoaComDadosService pessoaComDadosService;

    @PostMapping
    public PessoaComDadosDTO post(@Valid @RequestBody PessoaComDadosDTO pessoaComDadosDTO) {
        return pessoaComDadosService.create(pessoaComDadosDTO);
    }

    @GetMapping
    public List<PessoaComDadosDTO> get() {
        return pessoaComDadosService.list();
    }

    @DeleteMapping("/{cpf}")
    public void delete(@PathVariable("cpf") String cpf) throws EntidadeNaoEncontradaException {
        pessoaComDadosService.delete(cpf);
    }

    @PutMapping("/{cpf}") //TODO resolver: 400 connection closed
    public PessoaComDadosDTO put(@PathVariable("cpf") String cpf, PessoaComDadosDTO pessoaComDadosDTO) throws EntidadeNaoEncontradaException {
        return pessoaComDadosService.update(cpf, pessoaComDadosDTO);
    }

}
