package br.pucminas.moedaestudantil.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.pucminas.moedaestudantil.model.EmpresaParceira;

/** Acesso a dados de EmpresaParceira via Spring Data JPA. */
public interface EmpresaParceiraRepository extends JpaRepository<EmpresaParceira, Long> {
}
