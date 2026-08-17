package br.edu.infnet.elberth_api.domain;

public class Responsavel extends Pessoa {

    private String telefone;
    private boolean receberNotificacoes;
    
    public Responsavel() {

	}

	public Responsavel(Long id, String nome, String email, String telefone, boolean receberNotificacoes) {
		super(id, nome, email);
		this.telefone = telefone;
		this.receberNotificacoes = receberNotificacoes;
	}

	@Override
	public String toString() {

		return String.format("Responsável {%s, telefone=%s, receber notificações=%s}", 
				super.toString(),
				telefone,
				receberNotificacoes ? "sim": "não"
			);
	}
	
	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public boolean isReceberNotificacoes() {
		return receberNotificacoes;
	}

	public void setReceberNotificacoes(boolean receberNotificacoes) {
		this.receberNotificacoes = receberNotificacoes;
	}
}