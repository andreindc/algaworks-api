package com.algaworksapi.algaworksapi.repository;

import com.algaworksapi.algaworksapi.model.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {
}
