package br.edu.infnet.arquitetura.aluno;

public class AlunoEmailNaoEncontradoException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public AlunoEmailNaoEncontradoException(String email) {
        super("Aluno não encontrado com o e-mail: " + email);
    }
}