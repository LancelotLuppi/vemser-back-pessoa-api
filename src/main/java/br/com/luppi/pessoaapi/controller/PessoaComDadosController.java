package br.com.luppi.pessoaapi.controller;

import br.com.luppi.pessoaapi.dto.PessoaComDadosDTO;
import br.com.luppi.pessoaapi.service.PessoaComDadosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
    
}
