package br.pucminas.moedaestudantil.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.pucminas.moedaestudantil.model.Vantagem;

/** Acesso a dados de Vantagem via Spring Data JPA. */
public interface VantagemRepository extends JpaRepository<Vantagem, Long> {
}
