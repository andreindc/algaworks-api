package com.algaworksapi.algaworksapi.model;

import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name="pessoa")
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @NotNull
    private String nome;

    @NotNull
    private Boolean ativo;

    @Embedded
    private Endereco endereco;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    //Asi ignora esta propiedad en el JSON
/*
    @JsonIgnore
    @Transient
    public boolean isInativo(){
        return !this.ativo;
    }
*/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pessoa)) return false;
        Pessoa pessoa = (Pessoa) o;
        return Objects.equals(getCodigo(), pessoa.getCodigo()) &&
                Objects.equals(getNome(), pessoa.getNome()) &&
                Objects.equals(getAtivo(), pessoa.getAtivo()) &&
                Objects.equals(getEndereco(), pessoa.getEndereco());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCodigo(), getNome(), getAtivo(), getEndereco());
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "codigo=" + codigo +
                ", nome='" + nome + '\'' +
                ", ativo=" + ativo +
                ", endereco=" + endereco +
                '}';
    }
}
