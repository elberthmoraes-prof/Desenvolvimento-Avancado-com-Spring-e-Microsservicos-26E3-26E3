package br.edu.infnet.elberth_api.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Escola implements Identificavel {

	private Long id;
	private String nome;
	private String cidade;
	private boolean ativa;
	private double avaliacao;
	
	private final List<Turma> turmas = new ArrayList<Turma>();
	
	public Escola() {

	}
	
	public Escola(Long id, String nome, String cidade, boolean ativa, double avaliacao) {
		super();
		this.id = id;
		this.nome = nome;
		this.cidade = cidade;
		this.ativa = ativa;
		this.avaliacao = avaliacao;
	}

	public void adicionarTurma(Turma turma) {
		
		if(turma == null) {
			throw new IllegalArgumentException("A turma não pode ser nula!!!");
		}
		
		turmas.add(turma);
		turma.setEscola(this);
	}
	
	@Override
	public String toString() {

		return String.format("Escola {ID=%d, nome='%s', cidade='%s', ativa=%s, avaliacao=%.2f, qtdeTurmas=%d}", 
				id,
				nome,
				cidade,
				ativa ? "sim": "não",
				avaliacao,
				turmas.size()
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
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public boolean isAtiva() {
		return ativa;
	}
	public void setAtiva(boolean ativa) {
		this.ativa = ativa;
	}
	public double getAvaliacao() {
		return avaliacao;
	}
	public void setAvaliacao(double avaliacao) {
		this.avaliacao = avaliacao;
	}
	public List<Turma> getTurmas() {
		return Collections.unmodifiableList(turmas);
	}
}
