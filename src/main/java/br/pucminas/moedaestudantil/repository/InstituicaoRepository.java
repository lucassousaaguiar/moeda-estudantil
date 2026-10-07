package br.pucminas.moedaestudantil.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.pucminas.moedaestudantil.model.Instituicao;

/** Acesso a dados de Instituicao via Spring Data JPA. */
public interface InstituicaoRepository extends JpaRepository<Instituicao, Long> {
}
