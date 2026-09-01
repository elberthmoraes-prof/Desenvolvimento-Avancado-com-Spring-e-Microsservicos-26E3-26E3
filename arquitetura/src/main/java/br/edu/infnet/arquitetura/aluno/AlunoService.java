package br.edu.infnet.arquitetura.aluno;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class AlunoService {

	private final AlunoRepository alunoRepository;
	
	public AlunoService(AlunoRepository alunoRepository) {
		this.alunoRepository = alunoRepository;
	}

	public Aluno incluir(Aluno aluno) {
		return alunoRepository.save(aluno);
	}

	public List<Aluno> obterLista(){
		return alunoRepository.findAll();
	}

	public Aluno obterPorId(Long id) {
		return alunoRepository.findById(id).orElseThrow(() -> new AlunoNaoEncontratoException(id));
	}

	public Aluno alterar(Long id, Aluno aluno) {
		
		Aluno existente = obterPorId(id);
		
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

	public Optional<Aluno> obterPorEmail(String email) {
		return alunoRepository.findByEmail(email);
	}
}
