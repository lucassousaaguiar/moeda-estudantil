package br.pucminas.moedaestudantil.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.pucminas.moedaestudantil.model.Aluno;

/** Acesso a dados de Aluno via Spring Data JPA (padrão Repository/ORM). */
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
