package br.edu.infnet.arquitetura.turma;

public class TurmaNaoEncontradaException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public TurmaNaoEncontradaException(Long id) {
        super("Turma não encontrada. ID: " + id);
    }
}