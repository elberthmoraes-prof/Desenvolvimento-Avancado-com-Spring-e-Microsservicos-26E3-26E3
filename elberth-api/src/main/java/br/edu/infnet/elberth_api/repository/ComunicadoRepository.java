package br.edu.infnet.elberth_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.infnet.elberth_api.domain.Comunicado;

public interface ComunicadoRepository extends JpaRepository<Comunicado, Long> {

}
