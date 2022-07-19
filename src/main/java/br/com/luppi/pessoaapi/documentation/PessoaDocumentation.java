package br.com.luppi.pessoaapi.documentation;

import br.com.luppi.pessoaapi.dto.PessoaCreateDTO;
import br.com.luppi.pessoaapi.dto.PessoaDTO;
import br.com.luppi.pessoaapi.exception.EntidadeNaoEncontradaException;
import br.com.luppi.pessoaapi.exception.RegraDeNegocioException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

public interface PessoaDocumentation {

    @Operation(summary = "Adicionar pessoa", description = "Adiciona uma pessoa na aplicação")
        @ApiResponses(
                value = {
                        @ApiResponse(responseCode = "200", description = "Adiciona a pessoa"),
                        @ApiResponse(responseCode = "500", description = "Exception gerada")
                }
        )
    ResponseEntity<PessoaDTO> post(@Valid @RequestBody PessoaCreateDTO pessoa) throws RegraDeNegocioException;


    @Operation(summary = "Listar pessoas", description = "lista todas as pessoas cadastradas")
        @ApiResponses(
                value = {
                        @ApiResponse(responseCode = "200", description = "Retorna todas as pessoas cadastradas"),
                        @ApiResponse(responseCode = "500", description = "Exception gerada")
                }
        )
    ResponseEntity<List<PessoaDTO>> get();


    @Operation(summary = "Buscar por nome", description = "lista as pessoas através do nome desejado")
        @ApiResponses(
                value = {
                        @ApiResponse(responseCode = "200", description = "Retorna as pessoas pelo nome"),
                        @ApiResponse(responseCode = "500", description = "Exception gerada")
                }
        )
    ResponseEntity<List<PessoaDTO>> getByName(String nome);


    @Operation(summary = "Atualizar pessoa", description = "Atualiza os dados cadastrados de uma pessoa")
        @ApiResponses(
                value = {
                        @ApiResponse(responseCode = "200", description = "Dados atualizados"),
                        @ApiResponse(responseCode = "500", description = "Exception gerada")
                }
        )
    ResponseEntity<PessoaDTO> put(Integer id, @Valid @RequestBody PessoaCreateDTO pessoaAtualizada) throws RegraDeNegocioException, EntidadeNaoEncontradaException;


    @Operation(summary = "Remover pessoa", description = "remove uma pessoa através do id")
        @ApiResponses(
                value = {
                        @ApiResponse(responseCode = "200", description = "Cadastro removido"),
                        @ApiResponse(responseCode = "500", description = "Exception gerada")
                }
        )
    void delete(Integer id) throws RegraDeNegocioException, EntidadeNaoEncontradaException;
}
