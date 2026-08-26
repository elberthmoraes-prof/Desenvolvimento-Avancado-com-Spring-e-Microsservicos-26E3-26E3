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

    public List<Comunicado> obterLista() {
        return comunicadoRepository.findAll();
    }

	public void incluir(Comunicado comunicado) {
		
		comunicadoRepository.save(comunicado);
	}
	
	public void alterar(Long id, Comunicado comunicado) {
		
		Comunicado existente = obterPorId(id);
		
		existente.setConteudo(comunicado.getConteudo());
		existente.setDataPublicacao(comunicado.getDataPublicacao());
		existente.setPublicado(comunicado.isPublicado());
		existente.setTitulo(comunicado.getTitulo());
		
		comunicadoRepository.save(existente);
	}
	
	public void excluir(Long id) {
		
		Comunicado comunicado = obterPorId(id);
		
		comunicadoRepository.delete(comunicado);	
	}

    public Comunicado obterPorId(Long id) {

    	return comunicadoRepository.findById(id).orElseThrow(
    			() -> new RecursoNaoEncontradoException("Nenhum objeto encontrado para o identificador " + id + ".")
    		);
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

    public List<Comunicado> obterPorTitulo(String termo) {

        validarTermo(termo);

        String termoNormalizado = termo.toLowerCase();

        List<Comunicado> resultado = new ArrayList<>();

        for (Comunicado comunicado : obterLista()) {

            if (comunicado.getTitulo()
                    .toLowerCase()
                    .contains(termoNormalizado)) {

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
                .filter(comunicado ->
                        comunicado.getTitulo()
                                .toLowerCase()
                                .contains(termoNormalizado))
                .toList();
    }

    public List<Comunicado> ordenarPorTitulo() {

        return obterLista()
                .stream()
                .sorted(
                        Comparator.comparing(
                                Comunicado::getTitulo
                        )
                )
                .toList();
    }

    public List<String> obterTitulos() {

        return obterLista()
                .stream()
                .map(Comunicado::getTitulo)
                .toList();
    }

    private void validarTermo(String termo) {

        if (termo == null || termo.isBlank()) {
            throw new IllegalArgumentException(
                    "O termo de busca deve ser informado."
            );
        }
    }
}