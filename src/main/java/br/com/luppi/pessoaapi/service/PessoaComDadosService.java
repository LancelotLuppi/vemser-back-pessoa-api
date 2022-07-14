package br.com.luppi.pessoaapi.service;

import br.com.luppi.pessoaapi.client.DadosPessoaisClient;
import br.com.luppi.pessoaapi.dto.ContatoDTO;
import br.com.luppi.pessoaapi.dto.PessoaComDadosDTO;
import br.com.luppi.pessoaapi.dto.PessoaCreateDTO;
import br.com.luppi.pessoaapi.entity.Contato;
import br.com.luppi.pessoaapi.entity.Pessoa;
import br.com.luppi.pessoaapi.entity.PessoaComDados;
import br.com.luppi.pessoaapi.repository.PessoaComDadosRepository;
import br.com.luppi.pessoaapi.repository.PessoaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PessoaComDadosService {

    @Autowired
    private PessoaComDadosRepository pessoaComDadosRepository;
    @Autowired
    private ObjectMapper objectMapper;


    public PessoaComDadosDTO create(PessoaComDadosDTO pessoaComDadosDTO) {
        PessoaComDados pessoaComDados = returnEntity(pessoaComDadosDTO);
        return returnDTO(pessoaComDadosRepository.create(pessoaComDados));
    }


    public PessoaComDados returnEntity(PessoaComDadosDTO dto) {
        return objectMapper.convertValue(dto, PessoaComDados.class);
    }

    private PessoaComDadosDTO returnDTO(PessoaComDados entity) {
        return objectMapper.convertValue(entity, PessoaComDadosDTO.class);
    }
}
