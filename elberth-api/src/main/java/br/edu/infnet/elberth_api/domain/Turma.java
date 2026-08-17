package br.edu.infnet.elberth_api.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

public class Turma implements Identificavel {

	private Long id;
	private String nome;
	private int anoLetivo;
	private boolean ativa;
	
	private Escola escola;
	
	@JsonManagedReference
	private final List<Comunicado> comunicados = new ArrayList<Comunicado>();
	
	public Turma() {

	}

	public Turma(Long id, String nome, int anoLetivo, boolean ativa) {
		super();
		this.id = id;
		this.nome = nome;
		this.anoLetivo = anoLetivo;
		this.ativa = ativa;
	}

	public void adicionarComunicado(Comunicado comunicado){
		
		if(comunicado == null) {
			throw new IllegalArgumentException("O comunicado não pode ser nulo.");
		}
		
		comunicados.add(comunicado);
		comunicado.setTurma(this);
	}

	@Override
	public String toString() {

		String nomeEscola = escola != null ? escola.getNome() : "Sem escola";

		return String.format(
				"Turma {ID=%d, nome='%s', anoLetivo=%d, ativa=%s, escola=%s, comunicados=%d}",
				id,
				nome,
				anoLetivo,
				ativa ? "sim" : "não",
				nomeEscola,
				comunicados.size());
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

	public int getAnoLetivo() {
		return anoLetivo;
	}

	public void setAnoLetivo(int anoLetivo) {
		this.anoLetivo = anoLetivo;
	}

	public boolean isAtiva() {
		return ativa;
	}

	public void setAtiva(boolean ativa) {
		this.ativa = ativa;
	}

	public Escola getEscola() {
		return escola;
	}

	public void setEscola(Escola escola) {
		this.escola = escola;
	}

	public List<Comunicado> getComunicados() {
		return Collections.unmodifiableList(comunicados);
	}
}