package br.com.luppi.pessoaapi.repository;

import br.com.luppi.pessoaapi.client.DadosPessoaisClient;
import br.com.luppi.pessoaapi.dto.DadosPessoaisDTO;
import br.com.luppi.pessoaapi.dto.PessoaComDadosDTO;
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
        Pessoa pessoa = extrairPessoa(pessoaComDados);
        Pessoa pessoaCriada = pessoaRepository.create(pessoa);
        pessoaComDados.setIdPessoa(pessoaCriada.getIdPessoa());

        DadosPessoaisDTO dados = extrairDados(pessoaComDados);
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

    public PessoaComDados update(PessoaComDados cadastroRecuperado, PessoaComDados cadastroAtualizado) {
        Pessoa pessoaRecuperada = extrairPessoa(cadastroRecuperado);
        Pessoa pessoaAtualizada = extrairPessoa(cadastroAtualizado);
        pessoaRepository.update(pessoaRecuperada, pessoaAtualizada);

        DadosPessoaisDTO dadosAtualizados = extrairDados(cadastroAtualizado);
        dadosPessoaisClient.put(cadastroRecuperado.getCpf(), dadosAtualizados);

        cadastroRecuperado.setNome(cadastroAtualizado.getNome());
        cadastroRecuperado.setDataNascimento(cadastroAtualizado.getDataNascimento());
        cadastroRecuperado.setEmail(cadastroAtualizado.getEmail());
        cadastroRecuperado.setCnh(cadastroAtualizado.getCnh());
        cadastroRecuperado.setCpf(cadastroAtualizado.getCpf());
        cadastroRecuperado.setNomeMae(cadastroAtualizado.getNomeMae());
        cadastroRecuperado.setNomePai(cadastroAtualizado.getNomePai());
        cadastroRecuperado.setRg(cadastroAtualizado.getRg());
        cadastroRecuperado.setSexo(cadastroAtualizado.getSexo());
        cadastroRecuperado.setTituloEleitor(cadastroRecuperado.getTituloEleitor());
        return cadastroRecuperado;
    }

    public Pessoa extrairPessoa(PessoaComDados pessoaComDados) {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(pessoaComDados.getNome());
        pessoa.setCpf(pessoaComDados.getCpf());
        pessoa.setDataNascimento(pessoaComDados.getDataNascimento());
        pessoa.setEmail(pessoaComDados.getEmail());
        return pessoa;
    }

    public DadosPessoaisDTO extrairDados(PessoaComDados pessoaComDados) {
        DadosPessoaisDTO dados = new DadosPessoaisDTO();
        dados.setCnh(pessoaComDados.getCnh());
        dados.setCpf(pessoaComDados.getCpf());
        dados.setNome(pessoaComDados.getNome());
        dados.setNomeMae(pessoaComDados.getNomeMae());
        dados.setNomePai(pessoaComDados.getNomePai());
        dados.setRg(pessoaComDados.getRg());
        dados.setSexo(pessoaComDados.getSexo());
        dados.setTituloEleitor(pessoaComDados.getTituloEleitor());
        return dados;
    }
}
