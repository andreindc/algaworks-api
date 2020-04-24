package com.algaworksapi.algaworksapi.repository.lancamento;

import com.algaworksapi.algaworksapi.model.Lancamento;
import com.algaworksapi.algaworksapi.repository.fiter.LancamentoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface LancamentoRepositoryQuery {

    public Page<Lancamento> filtrar (LancamentoFilter lancamentoFilter, Pageable pageable);

}
