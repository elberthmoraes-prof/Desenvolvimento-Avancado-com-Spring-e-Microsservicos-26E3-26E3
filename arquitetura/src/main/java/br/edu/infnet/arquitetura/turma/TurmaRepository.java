package br.edu.infnet.arquitetura.turma;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TurmaRepository extends JpaRepository<Turma, Long> {

    List<Turma> findByAtivaTrue();

    List<Turma> findByNomeContainingIgnoreCase(String nome);
}