package br.pucminas.moedaestudantil.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Conta de moedas de um aluno ou professor. O professor recebe +1.000
 * moedas por semestre, acumuláveis (RN01).
 */
@Entity
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int saldo;

    public void creditar(int valor) {
        this.saldo += valor;
    }

    /** Debita apenas se houver saldo suficiente (RN02, RN04). */
    public boolean debitar(int valor) {
        if (valor > saldo) {
            return false;
        }
        this.saldo -= valor;
        return true;
    }

    public Long getId() {
        return id;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }
}
