package br.com.luppi.pessoaapi.repository;

import br.com.luppi.pessoaapi.client.DadosPessoaisClient;
import br.com.luppi.pessoaapi.dto.DadosPessoaisDTO;
import br.com.luppi.pessoaapi.entity.Pessoa;
import br.com.luppi.pessoaapi.entity.PessoaComDados;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PessoaComDadosRepository {
    private static List<PessoaComDados> listaPessoasComDados = new ArrayList<>();

    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private DadosPessoaisClient dadosPessoaisClient;


    public PessoaComDados create(PessoaComDados pessoaComDados) {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(pessoaComDados.getNome());
        pessoa.setCpf(pessoaComDados.getCpf());
        pessoa.setDataNascimento(pessoaComDados.getDataNascimento());
        pessoa.setEmail(pessoaComDados.getEmail());
        Pessoa pessoaCriada = pessoaRepository.create(pessoa);
        pessoaComDados.setIdPessoa(pessoaCriada.getIdPessoa());

        DadosPessoaisDTO dados = new DadosPessoaisDTO();
        dados.setCnh(pessoaComDados.getCnh());
        dados.setCpf(pessoaComDados.getCpf());
        dados.setNome(pessoaComDados.getNome());
        dados.setNomeMae(pessoaComDados.getNomeMae());
        dados.setNomePai(pessoaComDados.getNomePai());
        dados.setRg(pessoaComDados.getRg());
        dados.setSexo(pessoaComDados.getSexo());
        dados.setTituloEleitor(pessoaComDados.getTituloEleitor());
        dadosPessoaisClient.post(dados);

        listaPessoasComDados.add(pessoaComDados);
        return pessoaComDados;
    }

    public List<PessoaComDados> list() {
        return listaPessoasComDados;
    }

    public void delete(PessoaComDados pessoaComDados) {
        listaPessoasComDados.remove(pessoaComDados);
    }
}
