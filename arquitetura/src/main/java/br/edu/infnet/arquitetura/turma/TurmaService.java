package br.edu.infnet.arquitetura.turma;

import java.util.List;

import org.springframework.stereotype.Service;

import br.edu.infnet.arquitetura.aluno.Aluno;
import br.edu.infnet.arquitetura.aluno.AlunoService;
import br.edu.infnet.arquitetura.turma.dto.AlunoResumoResponse;
import br.edu.infnet.arquitetura.turma.dto.TurmaResponse;

@Service
public class TurmaService {

    private final TurmaRepository turmaRepository;
    private final AlunoService alunoService;

    public TurmaService(TurmaRepository turmaRepository, AlunoService alunoService) {
        this.turmaRepository = turmaRepository;
        this.alunoService = alunoService;
    }
    
    public TurmaResponse obterDetalhes(Long id) {
    	
    	Turma turma = obterPorId(id);
    	
    	return converterParaResponse(turma);
    }
    
    private TurmaResponse converterParaResponse(Turma turma) {
    	
    	List<AlunoResumoResponse> alunos = 
    			turma.getAlunos()
    			.stream()
    			.map(aluno -> 
    				new AlunoResumoResponse(aluno.getId(), aluno.getNome(), aluno.getEmail())
    			).toList();
    	
    	return new TurmaResponse(turma.getId(), turma.getNome(), turma.isAtiva(), alunos);
    }
    
    public Turma matricularAluno(Long turmaId, Long alunoId){
    	
    	Turma turma = obterPorId(turmaId);
    	
    	Aluno aluno = alunoService.obterPorId(alunoId);
    	
    	boolean jaMatriculado = turma.getAlunos().stream().anyMatch(alunoMatriculado -> alunoMatriculado.getId().equals(alunoId));
    	
    	if(jaMatriculado) {
        	throw new IllegalArgumentException("O aluno já está matriculado nesta turma.");
    	}

    	turma.adicionarAluno(aluno);
    	
    	return turmaRepository.save(turma);
    }

    public Turma incluir(Turma turma) {

        return turmaRepository.save(turma);
    }

    public List<Turma> obterLista() {
        return turmaRepository.findAll();
    }

    public Turma obterPorId(Long id) {
        return turmaRepository.findById(id).orElseThrow(() -> new TurmaNaoEncontradaException(id));
    }

    public Turma alterar(Long id, Turma turma) {

        Turma existente = obterPorId(id);

        existente.setNome(turma.getNome());
        existente.setAtiva(turma.isAtiva());

        return turmaRepository.save(existente);
    }

    public void excluir(Long id) {

        Turma existente = obterPorId(id);

        turmaRepository.delete(existente);
    }

    public List<Turma> obterAtivas() {
        return turmaRepository.findByAtivaTrue();
    }

    public List<Turma> obterPorNome(String nome) {
        return turmaRepository.findByNomeContainingIgnoreCase(nome);
    }
}