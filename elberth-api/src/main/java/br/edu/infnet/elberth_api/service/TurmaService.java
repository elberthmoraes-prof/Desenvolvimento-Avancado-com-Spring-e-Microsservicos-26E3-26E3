package br.edu.infnet.elberth_api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.edu.infnet.elberth_api.domain.Comunicado;
import br.edu.infnet.elberth_api.domain.Turma;
import br.edu.infnet.elberth_api.exception.RecursoNaoEncontradoException;
import br.edu.infnet.elberth_api.repository.ComunicadoRepository;
import br.edu.infnet.elberth_api.repository.TurmaRepository;

@Service
public class TurmaService {

    private final TurmaRepository turmaRepository;
    private final ComunicadoRepository comunicadoRepository;

    public TurmaService(TurmaRepository turmaRepository, ComunicadoRepository comunicadoRepository) {

        this.turmaRepository = turmaRepository;
        this.comunicadoRepository = comunicadoRepository;
    }

    public List<Turma> obterLista() {
        return turmaRepository.findAll();
    }

    public Turma obterPorId(Long id) {

        return turmaRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Turma não encontrada: " + id));
    }

    public List<Turma> obterPorAnoLetivo(int anoLetivo) {

        return turmaRepository.findByAnoLetivo(anoLetivo);
    }

    public Turma incluir(Turma turma) {

        turma.setId(null);

        return turmaRepository.save(turma);
    }

    public Turma alterar(Long id, Turma turma) {

        Turma turmaAtual = obterPorId(id);

        turmaAtual.setNome(turma.getNome());

        turmaAtual.setAnoLetivo(turma.getAnoLetivo());

        turmaAtual.setAtiva(turma.isAtiva());

        return turmaRepository.save(turmaAtual);
    }

    public void excluir(Long id) {

        Turma turma = obterPorId(id);

        turmaRepository.delete(turma);
    }

    public Comunicado adicionarComunicado(Long turmaId, Comunicado comunicado) {

        Turma turma = obterPorId(turmaId);

        comunicado.setId(null);

        turma.adicionarComunicado(comunicado);

        return comunicadoRepository.save(comunicado);
    }
}