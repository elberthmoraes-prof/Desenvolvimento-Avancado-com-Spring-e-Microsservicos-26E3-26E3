package br.edu.infnet.elberth_api.controller;

import java.net.URI;
import java.util.List;

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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.edu.infnet.elberth_api.domain.Comunicado;
import br.edu.infnet.elberth_api.domain.Turma;
import br.edu.infnet.elberth_api.service.TurmaService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/turmas")
public class TurmaController {

    private final TurmaService turmaService;

    public TurmaController(
            TurmaService turmaService) {

        this.turmaService = turmaService;
    }

    @Operation(
            summary = "Lista todas as turmas.",
            description = "Retorna todas as turmas cadastradas na aplicação."
    )
    @GetMapping
    public ResponseEntity<List<Turma>> obterLista() {

        List<Turma> turmas =
                turmaService.obterLista();

        return ResponseEntity.ok(turmas);
    }

    @GetMapping(params = "anoLetivo")
    public ResponseEntity<List<Turma>> obterPorAnoLetivo(
            @RequestParam int anoLetivo) {

        List<Turma> turmas =
                turmaService
                        .obterPorAnoLetivo(anoLetivo);

        return ResponseEntity.ok(turmas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turma> obterPorId(
            @PathVariable Long id) {

        Turma turma =
                turmaService.obterPorId(id);

        return ResponseEntity.ok(
                turma
        );
    }

    @PostMapping
    public ResponseEntity<Turma> incluir(@Valid
            @RequestBody Turma turma) {

        Turma turmaIncluida =
                turmaService.incluir(
                        turma
                );

        URI location =
                ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(
                                turmaIncluida.getId()
                        )
                        .toUri();

        return ResponseEntity
                .created(location)
                .body(turmaIncluida);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Turma> alterar(
            @PathVariable Long id,
            @Valid @RequestBody Turma turma) {

        Turma turmaAlterada =
                turmaService.alterar(
                        id,
                        turma
                );

        return ResponseEntity.ok(
                turmaAlterada
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(
            @PathVariable Long id) {

        turmaService.excluir(id);

        return ResponseEntity
                .noContent()
                .build();
    }

    @Operation(
            summary = "Adiciona um comunicado a uma turma.",
            description = "Cria um comunicado associado à turma informada."
    )
    @PostMapping("/{turmaId}/comunicados")
    public ResponseEntity<Comunicado> adicionarComunicado(
            @PathVariable Long turmaId,
            @RequestBody Comunicado comunicado) {

        Comunicado comunicadoIncluido =
                turmaService.adicionarComunicado(
                        turmaId,
                        comunicado
                );

        URI location =
                ServletUriComponentsBuilder
                        .fromCurrentContextPath()
                        .path("/comunicados/{id}")
                        .buildAndExpand(
                                comunicadoIncluido.getId()
                        )
                        .toUri();

        return ResponseEntity
                .created(location)
                .body(comunicadoIncluido);
    }
    
    
}