package br.edu.infnet.elberth_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.infnet.elberth_api.domain.Turma;

public interface TurmaRepository
        extends JpaRepository<Turma, Long> {

}