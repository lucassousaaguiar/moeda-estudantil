package br.pucminas.moedaestudantil.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.pucminas.moedaestudantil.model.Transacao;

/** Acesso a dados de Transacao (extrato) via Spring Data JPA. */
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
}
