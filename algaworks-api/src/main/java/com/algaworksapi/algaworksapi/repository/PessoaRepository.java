package com.algaworksapi.algaworksapi.repository;

import com.algaworksapi.algaworksapi.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
