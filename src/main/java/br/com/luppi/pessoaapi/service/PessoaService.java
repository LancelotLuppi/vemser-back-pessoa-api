package br.com.luppi.pessoaapi.service;

import br.com.luppi.pessoaapi.dto.PessoaCreateDTO;
import br.com.luppi.pessoaapi.dto.PessoaDTO;
import br.com.luppi.pessoaapi.entity.Pessoa;
import br.com.luppi.pessoaapi.exception.EntidadeNaoEncontradaException;
import br.com.luppi.pessoaapi.exception.RegraDeNegocioException;
import br.com.luppi.pessoaapi.repository.PessoaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PessoaService {
    @Autowired
    private  PessoaRepository pessoaRepository;
    @Autowired
    private ObjectMapper objectMapper;

    private final String NOT_FOUND_MESSAGE = "ID da pessoa nao encontrada";


    public PessoaDTO create(PessoaCreateDTO pessoaDto) throws RegraDeNegocioException {
        Pessoa pessoa = converterDTO(pessoaDto);
        return retornarDTO(pessoaRepository.create(pessoa));
    }
    public List<PessoaDTO> list(){
        return pessoaRepository.list().stream()
                .map(this::retornarDTO)
                .collect(Collectors.toList());
    }

    public PessoaDTO update(Integer id, PessoaCreateDTO pessoaDto) throws RegraDeNegocioException, EntidadeNaoEncontradaException {
        Pessoa pessoaAtualizada = converterDTO(pessoaDto);
        Pessoa pessoaRecuperada = returnPersonById(id);
        return retornarDTO(pessoaRepository.update(pessoaRecuperada, pessoaAtualizada));
    }

    public void delete(Integer id) throws EntidadeNaoEncontradaException {
        Pessoa pessoaRecuperada = returnPersonById(id);
        pessoaRepository.delete(pessoaRecuperada);
    }

    public List<PessoaDTO> listByName(String nome) {
        return pessoaRepository.list().stream()
                .filter(pessoa -> pessoa.getNome().toUpperCase().contains(nome.toUpperCase()))
                .map(this::retornarDTO)
                .collect(Collectors.toList());
    }

    public void verificarId(Integer idPessoa) throws  EntidadeNaoEncontradaException {
        pessoaRepository.list().stream()
                .filter(pessoa -> pessoa.getIdPessoa().equals(idPessoa))
                .findFirst()
                .orElseThrow(() -> new EntidadeNaoEncontradaException(NOT_FOUND_MESSAGE));
    }

    public Pessoa returnPersonById(Integer id) throws EntidadeNaoEncontradaException {
        return pessoaRepository.list().stream()
                .filter(pessoa -> pessoa.getIdPessoa().equals(id))
                .findFirst()
                .orElseThrow(() -> new EntidadeNaoEncontradaException(NOT_FOUND_MESSAGE));
    }

    public Pessoa converterDTO(PessoaCreateDTO dto) {
        return objectMapper.convertValue(dto, Pessoa.class);
    }

    public PessoaDTO retornarDTO(Pessoa pessoa) {
        return objectMapper.convertValue(pessoa, PessoaDTO.class);
    }
}
