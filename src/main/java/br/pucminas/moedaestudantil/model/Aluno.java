package br.pucminas.moedaestudantil.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;

/**
 * Aluno: cadastra-se com nome, email, CPF, RG, endereço, instituição e
 * curso (RF02); possui uma conta de moedas.
 */
@Entity
public class Aluno extends Usuario {

    @NotBlank(message = "O CPF é obrigatório")
    @Column(nullable = false, unique = true)
    private String cpf;

    @NotBlank(message = "O RG é obrigatório")
    @Column(nullable = false)
    private String rg;

    @NotBlank(message = "O endereço é obrigatório")
    @Column(nullable = false)
    private String endereco;

    @NotBlank(message = "O curso é obrigatório")
    @Column(nullable = false)
    private String curso;

    @ManyToOne(optional = false)
    @JoinColumn(name = "instituicao_id")
    private Instituicao instituicao;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "conta_id")
    private Conta conta;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public Instituicao getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }
}
