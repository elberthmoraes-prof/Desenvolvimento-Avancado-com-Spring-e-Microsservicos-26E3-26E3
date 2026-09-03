package br.edu.infnet.arquitetura.turma.dto;

import java.util.List;

public record TurmaResponse(Long id, String nome, boolean ativa, List<AlunoResumoResponse> alunos) {

}
