package br.edu.infnet.elberth_api.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import br.edu.infnet.elberth_api.domain.Comunicado;
import br.edu.infnet.elberth_api.exception.RecursoNaoEncontradoException;
import br.edu.infnet.elberth_api.repository.ComunicadoRepository;

@Service
public class ComunicadoService {

    private final ComunicadoRepository comunicadoRepository;

    public ComunicadoService(ComunicadoRepository comunicadoRepository) {

        this.comunicadoRepository = comunicadoRepository;
    }

    public Comunicado incluir(Comunicado comunicado) {

        comunicado.setId(null);

        return comunicadoRepository.save(comunicado);
    }

    public Comunicado obterPorId(Long id) {

        return comunicadoRepository
                .findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Comunicado não encontrado: " + id));
    }

    public List<Comunicado> obterLista() {

        return comunicadoRepository.findAll();
    }

    public Comunicado alterar(Long id, Comunicado comunicado) {

        Comunicado comunicadoAtual = obterPorId(id);

        comunicadoAtual.setTitulo(comunicado.getTitulo());

        comunicadoAtual.setConteudo(comunicado.getConteudo());

        comunicadoAtual.setPublicado(comunicado.isPublicado());

        comunicadoAtual.setDataPublicacao(comunicado.getDataPublicacao());

        return comunicadoRepository.save(comunicadoAtual);
    }

    public void excluir(Long id) {

        Comunicado comunicado = obterPorId(id);

        comunicadoRepository.delete(comunicado);
    }

    public List<Comunicado> obterPublicados() {

        List<Comunicado> publicados = new ArrayList<>();

        for (Comunicado comunicado : obterLista()) {

            if (comunicado.isPublicado()) {
                publicados.add(comunicado);
            }
        }

        return publicados;
    }

    public List<Comunicado> obterPublicadosDeclarativo() {

        return obterLista()
                .stream()
                .filter(Comunicado::isPublicado)
                .toList();
    }

    public List<Comunicado> obterPublicadosDoBanco() {

        return comunicadoRepository.findByPublicadoTrue();
    }

    public List<Comunicado> obterPorTitulo(String termo) {

        validarTermo(termo);

        String termoNormalizado = termo.toLowerCase();

        List<Comunicado> resultado = new ArrayList<>();

        for (Comunicado comunicado : obterLista()) {

            if (comunicado.getTitulo().toLowerCase().contains(termoNormalizado)) {

                resultado.add(comunicado);
            }
        }

        return resultado;
    }

    public List<Comunicado> buscarPorTituloDeclarativa(String termo) {

        validarTermo(termo);

        String termoNormalizado = termo.toLowerCase();

        return obterLista()
                .stream()
                .filter(comunicado -> comunicado.getTitulo().toLowerCase().contains(termoNormalizado))
                .toList();
    }

    public List<Comunicado> buscarPorTitulo(String termo) {

        validarTermo(termo);

        return comunicadoRepository.findByTituloContainingIgnoreCase(termo);
    }

    public List<Comunicado> ordenarPorTitulo() {

        return obterLista().stream().sorted(Comparator.comparing(Comunicado::getTitulo)).toList();
    }

    public List<String> obterTitulos() {

        return obterLista().stream().map(Comunicado::getTitulo).toList();
    }

    private void validarTermo(String termo) {

        if (termo == null || termo.isBlank()) {

            throw new IllegalArgumentException("O termo de busca deve ser informado.");
        }
    }
}