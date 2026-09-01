package br.edu.infnet.arquitetura.aluno;

public class AlunoNaoEncontratoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AlunoNaoEncontratoException(Long id) {
		super("Aluno não encontrado. ID: " + id);
	}
}
