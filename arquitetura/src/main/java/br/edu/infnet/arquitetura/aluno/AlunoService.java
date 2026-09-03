package br.edu.infnet.arquitetura.aluno;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class AlunoService {

	private final AlunoRepository alunoRepository;
	
	public AlunoService(AlunoRepository alunoRepository) {
		this.alunoRepository = alunoRepository;
	}

	public Aluno incluir(Aluno aluno) {

	    if (alunoRepository.findByEmail(aluno.getEmail()).isPresent()) {
	        throw new IllegalArgumentException("Já existe um aluno com o e-mail informado.");
	    }

	    return alunoRepository.save(aluno);
	}
	
	public List<Aluno> obterLista(){
		return alunoRepository.findAll();
	}

	public Aluno obterPorId(Long id) {
		return alunoRepository.findById(id).orElseThrow(() -> new AlunoNaoEncontradoException(id));
	}

	public Aluno alterar(Long id, Aluno aluno) {

	    Aluno existente = obterPorId(id);

	    alunoRepository.findByEmail(aluno.getEmail())
	        .filter(outroAluno -> !outroAluno.getId().equals(id))
	        .ifPresent(outroAluno -> {throw new IllegalArgumentException(
	                "Já existe outro aluno com o e-mail informado."
	            );
	        });

	    existente.setNome(aluno.getNome());
	    existente.setEmail(aluno.getEmail());
	    existente.setDataNascimento(aluno.getDataNascimento());
	    existente.setAtivo(aluno.isAtivo());

	    return alunoRepository.save(existente);
	}

	public void excluir(Long id) {
		
		Aluno existente = obterPorId(id);
		
		alunoRepository.delete(existente);
	}

	public List<Aluno> obterAtivos(){
		return alunoRepository.findByAtivoTrue();
	}

	public List<Aluno> obterPorNome(String nome){
		return alunoRepository.findByNomeContainingIgnoreCase(nome);
	}

	public Aluno obterPorEmail(String email) {
	    return alunoRepository.findByEmail(email).orElseThrow(() -> new AlunoEmailNaoEncontradoException(email));
	}
}
