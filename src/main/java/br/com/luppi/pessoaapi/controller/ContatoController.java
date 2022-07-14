package br.com.luppi.pessoaapi.controller;

import br.com.luppi.pessoaapi.documentation.ContatoDocumentation;
import br.com.luppi.pessoaapi.dto.ContatoCreateDTO;
import br.com.luppi.pessoaapi.dto.ContatoDTO;
import br.com.luppi.pessoaapi.exception.EntidadeNaoEncontradaException;
import br.com.luppi.pessoaapi.exception.RegraDeNegocioException;
import br.com.luppi.pessoaapi.service.ContatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@Validated
@RestController
@RequestMapping("/contato")
public class ContatoController implements ContatoDocumentation {
    @Autowired
    private ContatoService contatoService;


    @PostMapping("/{idPessoa}")
    public ResponseEntity<ContatoDTO> post(@PathVariable("idPessoa") Integer id,
                                             @RequestBody @Valid ContatoCreateDTO contato) throws RegraDeNegocioException, EntidadeNaoEncontradaException {
        return ResponseEntity.ok(contatoService.create(id, contato));
    }


    @GetMapping
    public ResponseEntity<List<ContatoDTO>>  get() {
        return ResponseEntity.ok(contatoService.list());
    }


    @PutMapping("/{idContato}")
    public ResponseEntity<ContatoDTO> put(@PathVariable("idContato") Integer id,
                          @RequestBody @Valid ContatoCreateDTO contatoAtualizado) throws RegraDeNegocioException, EntidadeNaoEncontradaException {
        return ResponseEntity.ok(contatoService.update(id, contatoAtualizado));
    }


    @DeleteMapping("/{idContato}")
    public void delete(@PathVariable("idContato") Integer id) throws RegraDeNegocioException, EntidadeNaoEncontradaException {
        contatoService.delete(id);
    }


    @GetMapping("/{idPessoa}")
    public ResponseEntity<List<ContatoDTO>> getByPersonId(@PathVariable("idPessoa") Integer id) throws EntidadeNaoEncontradaException {
        return ResponseEntity.ok(contatoService.listByPersonId(id));
    }
}
