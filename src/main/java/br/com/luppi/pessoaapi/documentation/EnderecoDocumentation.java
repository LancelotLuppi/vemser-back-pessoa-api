package br.com.luppi.pessoaapi.documentation;

import br.com.luppi.pessoaapi.dto.EnderecoCreateDTO;
import br.com.luppi.pessoaapi.dto.EnderecoDTO;
import br.com.luppi.pessoaapi.exception.EntidadeNaoEncontradaException;
import br.com.luppi.pessoaapi.exception.RegraDeNegocioException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EnderecoDocumentation {


    @Operation(summary = "Cadastrar novo endereço")
        @ApiResponses(
                value = {
                        @ApiResponse(responseCode = "200", description = "Retorna o endereço criado"),
                        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
                        @ApiResponse(responseCode = "404", description = "{idPessoa} não encontrada"),
                        @ApiResponse(responseCode = "500", description = "Exception gerada")
                }
        )
    ResponseEntity<EnderecoDTO> post(Integer id, EnderecoCreateDTO endereco) throws RegraDeNegocioException, EntidadeNaoEncontradaException;


    @Operation(summary = "Listar endereços cadastrados")
        @ApiResponses(
                value = {
                        @ApiResponse(responseCode = "200", description = "Retorna toda a lista de endereços"),
                        @ApiResponse(responseCode = "500", description = "Exception gerada")
                }
        )
    ResponseEntity<List<EnderecoDTO>> get();


    @Operation(summary = "Listar endereço por ID do endereço")
        @ApiResponses(
                value = {
                        @ApiResponse(responseCode = "200", description = "Retorna o contato através do ID"),
                        @ApiResponse(responseCode = "404", description = "{idEndereco} não encontrado"),
                        @ApiResponse(responseCode = "500", description = "Exception gerada")
                }
        )
    ResponseEntity<List<EnderecoDTO>> getByAddressId(Integer id) throws EntidadeNaoEncontradaException;



    @Operation(summary = "Listar endereços por ID da pessoa")
        @ApiResponses(
                value = {
                        @ApiResponse(responseCode = "200", description = "Retorna a lista de endereços por idPessoa"),
                        @ApiResponse(responseCode = "404", description = "{idPessoa} não encontrado"),
                        @ApiResponse(responseCode = "500", description = "Exception gerada")
                }
        )
    ResponseEntity<List<EnderecoDTO>>  getByPersonId(Integer id) throws EntidadeNaoEncontradaException;


    @Operation(summary = "Atualizar endereço através do {idEndereco}")
        @ApiResponses(
                value = {
                        @ApiResponse(responseCode = "200", description = "Endereço atualizado com sucesso"),
                        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
                        @ApiResponse(responseCode = "404", description = "{idEndereco} não encontrado"),
                        @ApiResponse(responseCode = "500", description = "Exception gerada")
                }
        )
    ResponseEntity<EnderecoDTO> put(Integer id, EnderecoCreateDTO enderecoAtualizado) throws RegraDeNegocioException, EntidadeNaoEncontradaException;


    @Operation(summary = "Deletar endereço através do {idEndereco}")
        @ApiResponses(
                value = {
                        @ApiResponse(responseCode = "200", description = "Endereço deletado com sucesso"),
                        @ApiResponse(responseCode = "404", description = "{idEndereco} não encontrado"),
                        @ApiResponse(responseCode = "500", description = "Exception gerada")
                }
        )
    void delete(Integer id) throws RegraDeNegocioException, EntidadeNaoEncontradaException;
}
