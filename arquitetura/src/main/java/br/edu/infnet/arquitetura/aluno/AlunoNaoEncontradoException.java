package br.edu.infnet.arquitetura.aluno;

public class AlunoNaoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AlunoNaoEncontradoException(Long id) {
		super("Aluno não encontrado. ID: " + id);
	}
}
