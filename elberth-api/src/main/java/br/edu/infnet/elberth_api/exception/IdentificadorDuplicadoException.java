package br.edu.infnet.elberth_api.exception;

public class IdentificadorDuplicadoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public IdentificadorDuplicadoException(String mensagem) {
		super(mensagem);
	}
}