package br.com.luppi.pessoaapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
public class PessoaCreateDTO {

    @Schema(description = "Nome da pessoa", example = "João Almeida")
    @NotBlank
    private String nome;

    @Schema(description = "AAAA-MM-DD, deve estar no passado", example = "2001-03-18")
    @Past
    @NotNull
    private LocalDate dataNascimento;

    @Schema(description = "CPF apenas numeros", example = "56448825002")
    @NotNull
    @Size(max = 11)
    private String cpf;

    @Schema(description = "seuEmail@dominio", example = "joao.almeida@dbccompany.com.br")
    @Email
    @Size(max = 255)
    private String email;
}
