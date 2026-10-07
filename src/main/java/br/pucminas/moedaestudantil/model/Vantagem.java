package br.pucminas.moedaestudantil.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/**
 * Vantagem oferecida por uma empresa parceira: descrição, foto e custo em
 * moedas (RN06).
 */
@Entity
public class Vantagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String descricao;

    /** Caminho/URL da foto do produto. */
    @Column(nullable = false)
    private String foto;

    @Column(nullable = false)
    private int custoEmMoedas;

    @ManyToOne(optional = false)
    @JoinColumn(name = "empresa_id")
    private EmpresaParceira empresa;

    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public int getCustoEmMoedas() {
        return custoEmMoedas;
    }

    public void setCustoEmMoedas(int custoEmMoedas) {
        this.custoEmMoedas = custoEmMoedas;
    }

    public EmpresaParceira getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EmpresaParceira empresa) {
        this.empresa = empresa;
    }
}
