package br.com.luppi.pessoaapi.service;

import br.com.luppi.pessoaapi.dto.ContatoCreateDTO;
import br.com.luppi.pessoaapi.dto.ContatoDTO;
import br.com.luppi.pessoaapi.entity.ContatoEntity;
import br.com.luppi.pessoaapi.exception.EntidadeNaoEncontradaException;
import br.com.luppi.pessoaapi.exception.RegraDeNegocioException;
import br.com.luppi.pessoaapi.repository.ContatoRepository;
import br.com.luppi.pessoaapi.repository.PessoaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContatoService {
    @Autowired
    private PessoaService pessoaService;
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private ContatoRepository contatoRepository;
    @Autowired
    private ObjectMapper objectMapper;


    public ContatoDTO create(Integer id, ContatoCreateDTO contatoDto) throws RegraDeNegocioException, EntidadeNaoEncontradaException {
        pessoaService.verificarId(id);
        contatoDto.setIdPessoa(id);
        ContatoEntity contato = retornarEntidade(contatoDto);
        return retornarDTO(contatoRepository.save(contato));
    }
    public List<ContatoDTO> list(){
        return contatoRepository.findAll().stream()
                .map(this::retornarDTO)
                .collect(Collectors.toList());
    }

    public ContatoDTO update(Integer id,ContatoCreateDTO contatoDto) throws EntidadeNaoEncontradaException {
        ContatoEntity contatoRecuperado = returnById(id);
        contatoRecuperado.setIdPessoa(contatoDto.getIdPessoa());
        contatoRecuperado.setTipoContato(contatoDto.getTipoContato());
        contatoRecuperado.setTelefone(contatoDto.getTelefone());
        contatoRecuperado.setDescricao(contatoDto.getDescricao());

        return retornarDTO(contatoRepository.save(contatoRecuperado));
    }

    public void delete(Integer id) throws RegraDeNegocioException, EntidadeNaoEncontradaException {
        ContatoEntity contatoRecuperado = returnById(id);
        contatoRepository.delete(contatoRecuperado);
    }

    public List<ContatoDTO> listByPersonId(Integer id) throws EntidadeNaoEncontradaException {
        pessoaService.verificarId(id);
        return contatoRepository.findAll().stream()
                .filter(contato -> contato.getIdPessoa().equals(id))
                .map(this::retornarDTO)
                .collect(Collectors.toList());
    }



    // Uteis-----------------------------------------------------------------------

    private ContatoEntity returnById(Integer id) throws EntidadeNaoEncontradaException {
        return contatoRepository.findById(id).stream()
                .findFirst()
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Contato não encontrado"));
    }

    private ContatoEntity retornarEntidade(ContatoCreateDTO dto) {
        return objectMapper.convertValue(dto, ContatoEntity.class);
    }

    private ContatoDTO retornarDTO(ContatoEntity contatoEntity) {
        return objectMapper.convertValue(contatoEntity, ContatoDTO.class);
    }
}
