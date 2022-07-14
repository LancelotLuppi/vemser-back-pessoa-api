package br.com.luppi.pessoaapi.controller;

import br.com.luppi.pessoaapi.documentation.EnderecoDocumentation;
import br.com.luppi.pessoaapi.dto.EnderecoCreateDTO;
import br.com.luppi.pessoaapi.dto.EnderecoDTO;
import br.com.luppi.pessoaapi.exception.EntidadeNaoEncontradaException;
import br.com.luppi.pessoaapi.exception.RegraDeNegocioException;
import br.com.luppi.pessoaapi.service.EnderecoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/endereco")
@Validated
public class EnderecoController implements EnderecoDocumentation {
    @Autowired
    private EnderecoService enderecoService;



    @PostMapping("/{idPessoa}")
    public ResponseEntity<EnderecoDTO> post(@PathVariable("idPessoa") Integer id,
                                              @RequestBody @Valid EnderecoCreateDTO endereco) throws RegraDeNegocioException, EntidadeNaoEncontradaException {
        return ResponseEntity.ok(enderecoService.create(id, endereco));
    }


    @GetMapping
    public ResponseEntity<List<EnderecoDTO>>  get() {
        return ResponseEntity.ok(enderecoService.list());
    }


    @GetMapping("/{idEndereco}")
    public ResponseEntity<List<EnderecoDTO>> getByAddressId(@PathVariable("idEndereco") Integer id) throws EntidadeNaoEncontradaException {
        return ResponseEntity.ok(enderecoService.listByAddressId(id));
    }


    @GetMapping("/{idPessoa}/pessoa")
    public ResponseEntity<List<EnderecoDTO>>  getByPersonId(@PathVariable("idPessoa") Integer id) throws EntidadeNaoEncontradaException {
        return ResponseEntity.ok(enderecoService.listByPersonId(id));
    }


    @PutMapping("/{idEndereco}")
    public ResponseEntity<EnderecoDTO> put(@PathVariable("idEndereco") Integer id,
                                 @RequestBody @Valid EnderecoCreateDTO enderecoAtualizado) throws RegraDeNegocioException, EntidadeNaoEncontradaException {
        return ResponseEntity.ok(enderecoService.update(id, enderecoAtualizado));
    }


    @DeleteMapping("/{idEndereco}")
    public void delete(@PathVariable("idEndereco") Integer id) throws RegraDeNegocioException, EntidadeNaoEncontradaException {
        enderecoService.delete(id);
    }

}
