package br.edu.infnet.elberth_api;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.edu.infnet.elberth_api.domain.Comunicado;
import br.edu.infnet.elberth_api.domain.Turma;
import br.edu.infnet.elberth_api.repository.ComunicadoRepository;
import br.edu.infnet.elberth_api.repository.TurmaRepository;

@Component
public class ProjetoRunner implements CommandLineRunner {

    @Value("${app.runner.enabled:false}")
    private boolean runnerEnabled;

    private final TurmaRepository turmaRepository;
    private final ComunicadoRepository comunicadoRepository;

    public ProjetoRunner(TurmaRepository turmaRepository, ComunicadoRepository comunicadoRepository) {
        this.turmaRepository = turmaRepository;
        this.comunicadoRepository = comunicadoRepository;
    }

    @Override
    public void run(String... args) {

        if (!runnerEnabled) {
            return;
        }

        carregarDadosIniciais();
    }

    private void carregarDadosIniciais() {

        System.out.println();
        System.out.println("========================================");
        System.out.println("CARREGANDO DADOS INICIAIS");
        System.out.println("========================================");

        Turma turmaJava = criarTurma("Turma Java", 2026, true);

        Turma turmaSpring = criarTurma("Turma Spring Boot", 2026, true);

        criarComunicado(turmaJava, "Aula de JPA", "Relacionamentos com Spring Data JPA.", true, LocalDateTime.now());

        criarComunicado(turmaJava, "Trabalho de Java", "Entrega do trabalho na próxima semana.", false, null);

        criarComunicado(turmaSpring, "API REST", "Revisão dos endpoints da aplicação.", true, LocalDateTime.now());

        System.out.println();
        System.out.println("Turmas cadastradas: " + turmaRepository.count());
        System.out.println("Comunicados cadastrados: " + comunicadoRepository.count());
        System.out.println("Dados iniciais carregados com sucesso.");
        System.out.println("========================================");
    }

    private Turma criarTurma(String nome, int anoLetivo, boolean ativa) { 

        Turma turma = new Turma(null, nome, anoLetivo, ativa);

        return turmaRepository.save(turma);
    }

    private Comunicado criarComunicado(Turma turma, String titulo, String conteudo, boolean publicado, LocalDateTime dataPublicacao) {

        Comunicado comunicado = new Comunicado(titulo, conteudo, publicado, dataPublicacao);

        turma.adicionarComunicado(comunicado);

        return comunicadoRepository.save(comunicado);
    }
}