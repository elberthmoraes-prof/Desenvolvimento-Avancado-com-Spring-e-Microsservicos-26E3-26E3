package br.edu.infnet.elberth_api.domain;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "comunicados")
public class Comunicado implements Identificavel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 150)
	@NotBlank(message = "O título deve ser informado.")
	@Size(max = 150, message = "O título deve possuir no máximo 150 caracteres.")
	private String titulo;
	
	@Column(nullable = false, length = 2000)
	@NotBlank(message = "O conteúdo deve ser informado.")
	@Size(max = 2000, message = "O conteúdo deve possuir no máximo 2000 caracteres.")
	private String conteudo;
	
	private boolean publicado;
	private LocalDateTime dataPublicacao;
	
	@ManyToOne
	@JoinColumn(name = "turma_id")
	@JsonBackReference
	private Turma turma;
	
	protected Comunicado() {
	}

	public Comunicado(String titulo, String conteudo) {
		this.titulo = titulo;
		this.conteudo = conteudo;
	}
	public Comunicado(String titulo, String conteudo, boolean publicado, LocalDateTime dataPublicacao) {
		this(titulo, conteudo);
		this.publicado = publicado;
		this.dataPublicacao = dataPublicacao;
	}
	public Comunicado(Long id, String titulo, String conteudo, boolean publicado, LocalDateTime dataPublicacao) {
		this(titulo, conteudo, publicado, dataPublicacao);
		this.id = id;
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
