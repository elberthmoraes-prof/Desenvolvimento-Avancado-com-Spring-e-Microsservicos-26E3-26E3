package br.edu.infnet.arquitetura.turma;

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

import br.edu.infnet.arquitetura.turma.dto.TurmaResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/turmas")
public class TurmaController {

    private final TurmaService turmaService;

    public TurmaController(TurmaService turmaService) {
        this.turmaService = turmaService;
    }
    
    @GetMapping("{id}/detalhes")
    public ResponseEntity<TurmaResponse> obterDetalhes(@PathVariable Long id) {
    
    	TurmaResponse turmaResponse = turmaService.obterDetalhes(id);
    	   	
    	return ResponseEntity.ok(turmaResponse);
    }
    
    @PostMapping("/{turmaId}/alunos/{alunoId}")
    public ResponseEntity<Turma> matricularAluno(@PathVariable Long turmaId, @PathVariable Long alunoId){
    	
    	Turma matricula = turmaService.matricularAluno(turmaId, alunoId);
    	
    	return ResponseEntity.ok(matricula);
    }

    @PostMapping
    public ResponseEntity<Turma> incluir(@Valid @RequestBody Turma turma) {

        return ResponseEntity.status(HttpStatus.CREATED).body(turmaService.incluir(turma));
    }

    @GetMapping
    public ResponseEntity<List<Turma>> obterLista() {

        return ResponseEntity.ok(turmaService.obterLista());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turma> obterPorId(@PathVariable Long id) {

        return ResponseEntity.ok(turmaService.obterPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Turma> alterar(@PathVariable Long id, @Valid @RequestBody Turma turma) {

        return ResponseEntity.ok(turmaService.alterar(id, turma));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {

        turmaService.excluir(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/ativas")
    public ResponseEntity<List<Turma>> obterAtivas() {

        return ResponseEntity.ok(turmaService.obterAtivas());
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Turma>> obterPorNome(@RequestParam String nome) {

        return ResponseEntity.ok(turmaService.obterPorNome(nome));
    }
}