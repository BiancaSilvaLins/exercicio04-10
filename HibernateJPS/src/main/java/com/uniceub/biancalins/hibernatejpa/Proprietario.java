/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uniceub.biancalins.hibernatejpa;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author bianca.lins
 */
@Entity
@Table(name = "tb_proprietario")
public class Proprietario implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "nome_proprietario", nullable = false)
    private String nome;

    @Column(name = "cpf_proprietario", nullable = false)
    private String cpf;

    @OneToMany(mappedBy = "proprietario")
    private List<Veiculo> veiculo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Veiculo getVeiculo() {
        return (Veiculo) veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = (List<Veiculo>) veiculo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Proprietario other = (Proprietario) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
