package com.algaworksapi.algaworksapi.service;

import com.algaworksapi.algaworksapi.model.Pessoa;
import com.algaworksapi.algaworksapi.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PesooaService {

    @Autowired
    private PessoaRepository pessoaRepository;

/*
    public ResponseEntity<Optional<Pessoa>> atualizar(Long codigo, Pessoa pessoa){
        Optional<Pessoa> pessoaSalva = pessoaRepository.findById(codigo);
        if (pessoaSalva == null) {
            throw new EmptyResultDataAccessException(1);
        }
        if(pessoaSalva.isPresent()){
            BeanUtils.copyProperties(pessoa,pessoaSalva,"codigo");
            pessoaRepository.save(pessoa);
        }
        return ResponseEntity.ok(pessoaSalva);

    }


    public ResponseEntity<Optional<Pessoa>> actualizarPropiedadAtivo(Long codigo, Boolean ativo) {
        Optional<Pessoa> pessoaSalva = buscarPessoaPeloCodigo(codigo);
        pessoaSalva.setAtivo(ativo);
        pessoaRepository.save(pessoaSalva);
        return pessoaSalva;
    }


    public Optional<Pessoa> buscarPessoaPeloCodigo(Long codigo) {
        Optional<Pessoa> pessoaSalva = pessoaRepository.findById(codigo);
        if (pessoaSalva == null) {
            throw new EmptyResultDataAccessException(1);
        }
        return pessoaSalva;
    }
*/

}
