package br.pucminas.moedaestudantil.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/**
 * Resgate de uma vantagem pelo aluno, com código de cupom gerado pelo
 * sistema (RN04, RN05).
 */
@Entity
@DiscriminatorValue("RESGATE")
public class ResgateVantagem extends Transacao {

    private String codigoCupom;

    @ManyToOne
    @JoinColumn(name = "vantagem_id")
    private Vantagem vantagem;

    public String getCodigoCupom() {
        return codigoCupom;
    }

    public void setCodigoCupom(String codigoCupom) {
        this.codigoCupom = codigoCupom;
    }

    public Vantagem getVantagem() {
        return vantagem;
    }

    public void setVantagem(Vantagem vantagem) {
        this.vantagem = vantagem;
    }
}
