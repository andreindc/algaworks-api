package com.algaworksapi.algaworksapi.resource;

import com.algaworksapi.algaworksapi.event.RecursoCriadoEvent;
import com.algaworksapi.algaworksapi.model.Endereco;
import com.algaworksapi.algaworksapi.model.Pessoa;
import com.algaworksapi.algaworksapi.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pesooas")
public class PessoaResource {
    @Autowired
    private PessoaRepository pessoaRepository;

    @GetMapping
    public List<Pessoa> listar(){
        return pessoaRepository.findAll();
    }

    @Autowired //Disparando un evento de aplicación, necesito validar que no este duplicado
    private ApplicationEventPublisher publisher;

    @PostMapping
    public ResponseEntity<Pessoa> criar(@Valid @RequestBody Pessoa pessoa, HttpServletResponse response){
        Pessoa  pessoaSalva = pessoaRepository.save(pessoa);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, pessoaSalva.getCodigo()));
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva);
    }

    @GetMapping("/{codigo}")
    public Optional<Pessoa> buscarPeloCodigo(@PathVariable Long codigo){
        return pessoaRepository.findById(codigo);
    }
}
