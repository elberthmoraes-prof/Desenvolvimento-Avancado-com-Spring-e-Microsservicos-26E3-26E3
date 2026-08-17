package br.edu.infnet.elberth_api.domain;

public class Professor extends Pessoa {

    private String matricula;
    private boolean ativo;
    
    public Professor() {

	}

	public Professor(Long id, String nome, String email, String matricula, boolean ativo) {
		super(id, nome, email);
		this.matricula = matricula;
		this.ativo = ativo;
	}

	@Override
	public String toString() {

		return String.format("Professor {%s, matrícula=%s, ativo=%s}", 
				super.toString(),
				matricula,
				ativo ? "sim": "não"
			);
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
}