package br.pucminas.moedaestudantil.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.pucminas.moedaestudantil.model.Professor;

/** Acesso a dados de Professor via Spring Data JPA. */
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
}
