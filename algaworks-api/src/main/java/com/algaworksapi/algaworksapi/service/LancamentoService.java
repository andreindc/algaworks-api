package com.algaworksapi.algaworksapi.service;

import com.algaworksapi.algaworksapi.model.Lancamento;
import com.algaworksapi.algaworksapi.model.Pessoa;
import com.algaworksapi.algaworksapi.repository.LancamentoRepository;
import com.algaworksapi.algaworksapi.repository.PessoaRepository;
import com.algaworksapi.algaworksapi.service.exception.PessoaInexistenteOuInativaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LancamentoService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private LancamentoRepository lancamentoRepository;

    public Lancamento salvar(Lancamento lancamento) {
        Optional<Pessoa> pessoa = pessoaRepository.findById(lancamento.getPessoa().getCodigo());
        if (pessoa.isPresent()) {
            if (!pessoa.get().getAtivo()) {
                throw new PessoaInexistenteOuInativaException();
            }
        }
            return lancamentoRepository.save(lancamento);
    }

}