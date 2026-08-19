package br.edu.infnet.elberth_api.domain;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "comunicados")
public class Comunicado implements Identificavel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, length = 150)
	private String titulo;
	@Column(nullable = false, length = 2000)
	private String conteudo;
	private boolean publicado;
	private LocalDateTime dataPublicacao;
	
	@JsonBackReference
	@Transient
	private Turma turma;
	
	protected Comunicado() {
	}

	public Comunicado(String titulo, String conteudo) {
		this.titulo = titulo;
		this.conteudo = conteudo;
	}
	public Comunicado(Long id, String titulo, String conteudo, boolean publicado, LocalDateTime dataPublicacao) {
		this(titulo, conteudo);
		this.id = id;
		this.publicado = publicado;
		this.dataPublicacao = dataPublicacao;
	}

	@Override
	public String toString() {
		
		String nomeTurma = turma != null ? turma.getNome() : "Sem turma";

		return String.format("Comunicado {ID=%d, titulo='%s', conteudo='%s', publicado=%s, dataPublicacao=%s, turma=%s}", 
				id,
				titulo,
				conteudo,
				publicado ? "sim": "não",
				dataPublicacao,		
				nomeTurma
			);
	}

	@Override
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public boolean isPublicado() {
		return publicado;
	}

	public void setPublicado(boolean publicado) {
		this.publicado = publicado;
	}

	public LocalDateTime getDataPublicacao() {
		return dataPublicacao;
	}

	public void setDataPublicacao(LocalDateTime dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}
}
