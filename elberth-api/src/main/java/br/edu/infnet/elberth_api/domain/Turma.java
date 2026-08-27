package br.edu.infnet.elberth_api.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "turmas")
public class Turma implements Identificavel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(
            message = "O nome da turma deve ser informado."
    )
    @Size(
            max = 100,
            message = "O nome da turma deve possuir no máximo 100 caracteres."
    )
    private String nome;

    @Min(
            value = 2000,
            message = "O ano letivo deve ser igual ou superior a 2000."
    )
    private int anoLetivo;

    private boolean ativa;

    @Transient
    private Escola escola;

    @OneToMany(mappedBy = "turma")
    @JsonManagedReference
    private List<Comunicado> comunicados =
            new ArrayList<Comunicado>();

    public Turma() {

    }

    public Turma(
            Long id,
            String nome,
            int anoLetivo,
            boolean ativa) {

        this.id = id;
        this.nome = nome;
        this.anoLetivo = anoLetivo;
        this.ativa = ativa;
    }

    public void adicionarComunicado(
            Comunicado comunicado) {

        if (comunicado == null) {
            throw new IllegalArgumentException(
                    "O comunicado não pode ser nulo."
            );
        }

        comunicados.add(comunicado);
        comunicado.setTurma(this);
    }

    @Override
    public String toString() {

        String nomeEscola =
                escola != null
                        ? escola.getNome()
                        : "Sem escola";

        return String.format(
                "Turma {ID=%d, nome='%s', anoLetivo=%d, ativa=%s, escola=%s, comunicados=%d}",
                id,
                nome,
                anoLetivo,
                ativa ? "sim" : "não",
                nomeEscola,
                comunicados.size()
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
        return Collections.unmodifiableList(
                comunicados
        );
    }
}