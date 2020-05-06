package com.algaworksapi.algaworksapi.repository.fiter;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class LancamentoFilter {

    private String decricao;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate dataVencimento;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate dataVencimentoAte;

    public String getDecricao() {
        return decricao;
    }

    public void setDecricao(String descricao) {
        this.decricao = decricao;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public LocalDate getDataVencimentoAte() {
        return dataVencimentoAte;
    }

    public void setDataVencimentoAte(LocalDate dataVencimentoAte) {
        this.dataVencimentoAte = dataVencimentoAte;
    }
}
