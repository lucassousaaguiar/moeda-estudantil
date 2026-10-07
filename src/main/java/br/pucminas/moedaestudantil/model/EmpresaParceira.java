package br.pucminas.moedaestudantil.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

/**
 * Empresa parceira: cadastra-se no sistema e oferece vantagens (RF03, RF10).
 */
@Entity
public class EmpresaParceira extends Usuario {

    @NotBlank(message = "O CNPJ é obrigatório")
    @Column(nullable = false, unique = true)
    private String cnpj;

    @OneToMany(mappedBy = "empresa")
    private List<Vantagem> vantagens = new ArrayList<>();

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public List<Vantagem> getVantagens() {
        return vantagens;
    }
}
