package br.edu.infnet.arquitetura.aluno;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

	List<Aluno> findByAtivoTrue();
	
	List<Aluno> findByNomeContainingIgnoreCase(String nome);

	Optional<Aluno> findByEmail(String email);
}