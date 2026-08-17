package br.edu.infnet.elberth_api.domain;

public abstract class Pessoa implements Identificavel {
	
	private Long id;
	private String nome;
    private String email;

    public Pessoa() {

	}

	public Pessoa(Long id, String nome, String email) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
	}

	@Override
	public String toString() {

		return String.format("ID=%d, nome='%s', e-mail='%s'", 
				id,
				nome,
				email
			);
	}

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
