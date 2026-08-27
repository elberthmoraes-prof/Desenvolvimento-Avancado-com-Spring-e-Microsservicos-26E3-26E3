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
import br.edu.infnet.elberth_api.service.ComunicadoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/comunicados")
public class ComunicadoController {

    private final ComunicadoService comunicadoService;

    public ComunicadoController(ComunicadoService comunicadoService) {

        this.comunicadoService = comunicadoService;
    }

    @Operation(
            summary = "Lista todos os comunicados.",
            description = "Retorna todos os comunicados cadastrados na aplicação."
    )
    @GetMapping
    public ResponseEntity<List<Comunicado>> obterLista() {

        return ResponseEntity.ok(comunicadoService.obterLista());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comunicado> obterPorId(@PathVariable Long id) {

        Comunicado comunicado = comunicadoService.obterPorId(id);

        return ResponseEntity.ok(comunicado);
    }

    @PostMapping
    public ResponseEntity<Comunicado> incluir(@Valid @RequestBody Comunicado comunicado) {

        Comunicado comunicadoIncluido = comunicadoService.incluir(comunicado);

        URI location =
                ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(comunicadoIncluido.getId())
                        .toUri();

        return ResponseEntity.created(location).body(comunicadoIncluido);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comunicado> alterar(@PathVariable Long id, @Valid @RequestBody Comunicado comunicado) {

        Comunicado comunicadoAlterado = comunicadoService.alterar(id, comunicado);

        return ResponseEntity.ok(comunicadoAlterado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {

        comunicadoService.excluir(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/publicados")
    public ResponseEntity<List<Comunicado>> obterPublicados() {

        return ResponseEntity.ok(comunicadoService.obterPublicadosDoBanco());
    }

    @GetMapping("/busca")
    public ResponseEntity<List<Comunicado>> buscarPorTitulo(@Parameter(description = "Trecho do título do comunicado.") @RequestParam String titulo) {

        return ResponseEntity.ok(comunicadoService.buscarPorTitulo(titulo));
    }
}