package br.com.luppi.pessoaapi.repository;

import br.com.luppi.pessoaapi.entity.PessoaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public interface PessoaRepository extends JpaRepository<PessoaEntity, Integer> {
}
