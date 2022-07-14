package br.com.luppi.pessoaapi.service;

import br.com.luppi.pessoaapi.client.DadosPessoaisClient;
import br.com.luppi.pessoaapi.dto.PessoaComDadosDTO;
import br.com.luppi.pessoaapi.entity.Pessoa;
import br.com.luppi.pessoaapi.entity.PessoaComDados;
import br.com.luppi.pessoaapi.exception.EntidadeNaoEncontradaException;
import br.com.luppi.pessoaapi.repository.PessoaComDadosRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PessoaComDadosService {

    @Autowired
    private PessoaComDadosRepository pessoaComDadosRepository;
    @Autowired
    private PessoaService pessoaService;
    @Autowired
    private DadosPessoaisClient dadosPessoaisClient;
    @Autowired
    private ObjectMapper objectMapper;


    public PessoaComDadosDTO create(PessoaComDadosDTO pessoaComDadosDTO) {
        PessoaComDados pessoaComDados = returnEntity(pessoaComDadosDTO);
        return returnDTO(pessoaComDadosRepository.create(pessoaComDados));
    }

    public List<PessoaComDadosDTO> list() {
        return pessoaComDadosRepository.list().stream()
                .map(this::returnDTO)
                .collect(Collectors.toList());
    }

    public void delete(String cpf) throws EntidadeNaoEncontradaException {
        Pessoa pessoaRecuperada = pessoaService.returnByCpf(cpf);
        pessoaService.delete(pessoaRecuperada.getIdPessoa());
        dadosPessoaisClient.delete(cpf);
        PessoaComDados pessoaComDadosRecuperada = returnByCpf(cpf);
        pessoaComDadosRepository.delete(pessoaComDadosRecuperada);
    }

    public PessoaComDados returnByCpf(String cpf) throws EntidadeNaoEncontradaException {
        return pessoaComDadosRepository.list().stream()
                .filter(pessoaComDados -> pessoaComDados.getCpf().equals(cpf))
                .findFirst()
                .orElseThrow(() -> new EntidadeNaoEncontradaException("CPF nao encontrado"));
    }

    public PessoaComDados returnEntity(PessoaComDadosDTO dto) {
        return objectMapper.convertValue(dto, PessoaComDados.class);
    }

    private PessoaComDadosDTO returnDTO(PessoaComDados entity) {
        return objectMapper.convertValue(entity, PessoaComDadosDTO.class);
    }
}
