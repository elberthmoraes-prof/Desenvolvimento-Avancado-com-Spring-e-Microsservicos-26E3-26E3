package br.edu.infnet.arquitetura.aluno;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

	private final AlunoService alunoService;
	
	public AlunoController(AlunoService alunoService) {
		this.alunoService = alunoService;
	}
	
	@PostMapping
	public ResponseEntity<Aluno> incluir(@Valid @RequestBody Aluno aluno){
		
		Aluno incluido = alunoService.incluir(aluno);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(incluido);
	}

	@GetMapping
	public List<Aluno> obterLista(){
		
		return alunoService.obterLista();
	}
	
	@GetMapping("/{id}")
	public Aluno obterPorId(@PathVariable Long id){
		
		return alunoService.obterPorId(id);
	}

	@PutMapping("/{id}")
	public Aluno alterar(@PathVariable Long id, @Valid @RequestBody Aluno aluno) {
		
		aluno.setId(id);
		
		return alunoService.alterar(id, aluno);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id){
		
		alunoService.excluir(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/ativos")
	public List<Aluno> obterAtivos(){
		
		return alunoService.obterAtivos();
	}
	
	@GetMapping("/buscar")
	public List<Aluno> obterPorNome(@RequestParam String nome){
		
		return alunoService.obterPorNome(nome);
	}
}
