package br.com.luppi.pessoaapi.entity;

import br.com.luppi.pessoaapi.enums.TipoContato;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "CONTATO")
public class ContatoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CONTATO_SEQ")
    @SequenceGenerator(name = "CONTATO_SEQ", sequenceName = "seq_contato", allocationSize = 1)
    @Column(name = "id_contato")
    private Integer idContato;

    @Column(name = "id_pessoa")
    private Integer idPessoa;

    @Column(name = "tipo")
    private TipoContato tipoContato;

    @Column(name = "numero")
    private String telefone;

    @Column(name = "descricao")
    private String descricao;

}
