package br.edu.infnet.elberth_api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.edu.infnet.elberth_api.domain.Comunicado;
import br.edu.infnet.elberth_api.repository.ComunicadoRepository;

@Service
public class ComunicadoService extends BaseService<Comunicado> {

    private final ComunicadoRepository comunicadoRepository;
    
    public ComunicadoService(ComunicadoRepository comunicadoRepository) {
		this.comunicadoRepository = comunicadoRepository;
	}
	
	
	public List<Comunicado> obterLista() {
		return comunicadoRepository.findAll();
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
				.filter(comunicado -> comunicado.getTitulo()
						.toLowerCase()
						.contains(termoNormalizado))
				.toList();
	}
	
	private void validarTermo(String termo) {

        if (
            termo == null
            || termo.isBlank()
        ) {
            throw new IllegalArgumentException(
                "O termo de busca deve ser informado."
            );
        }
    }
	
	public List<Comunicado> ordenarPorTitulo() {
		// TODO Auto-generated method stub
		return new ArrayList<Comunicado>();
	}

	public List<String> obterTitulos() {
		// TODO Auto-generated method stub
		return new ArrayList<String>();
	}
}