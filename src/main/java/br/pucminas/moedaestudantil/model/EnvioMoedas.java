package br.pucminas.moedaestudantil.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/**
 * Envio de moedas de um professor a um aluno, com motivo obrigatório (RN02).
 */
@Entity
@DiscriminatorValue("ENVIO")
public class EnvioMoedas extends Transacao {

    private String motivo;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Professor professor;

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
}
