package com.algaworksapi.algaworksapi.resource;

import com.algaworksapi.algaworksapi.event.RecursoCriadoEvent;
import com.algaworksapi.algaworksapi.model.Pessoa;
import com.algaworksapi.algaworksapi.repository.PessoaRepository;
import com.algaworksapi.algaworksapi.service.PesooaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pesooas")
public class PessoaResource {
    @Autowired
    private PessoaRepository pessoaRepository;

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_PESSOA') and #oauth2.hasScope('read')")
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
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_PESSOA') and #oauth2.hasScope('read')")
    public ResponseEntity<Optional<Pessoa>> buscarPeloCodigo(@PathVariable Long codigo){
        Optional<Pessoa> pessoa = pessoaRepository.findById(codigo);
        return pessoa != null ? ResponseEntity.ok(pessoa) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ROLE_REMOVER_PESSOA') and #oauth2.hasScope('write')")
    public void remover(@PathVariable Long codigo){
        pessoaRepository.deleteById(codigo);
    }

    @PutMapping("/{codigo}")
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_PESSOA') and #oauth2.hasScope('write')")
    public ResponseEntity<Pessoa> actualizar(@PathVariable Long codigo, @Valid @RequestBody Pessoa pessoa){
        Optional<Pessoa> pessoaSalva = pessoaRepository.findById(codigo);
        BeanUtils.copyProperties(pessoa,pessoaSalva,"codigo");
        pessoa.setCodigo(codigo);
        pessoaRepository.save(pessoa);
        return ResponseEntity.ok(pessoa);
    }



   /*  @PutMapping("/{codigo}")
    public ResponseEntity<Pessoa> actualizar(@PathVariable Long codigo, @Valid @RequestBody Pessoa pessoa){
      //  ResponseEntity<Optional<Pessoa>> pessoaSalva = PesooaService.atualizar(codigo, pessoa);
        pessoa.setCodigo(codigo);
        pessoaRepository.save(pessoa);
        return ResponseEntity.ok(pessoa);
    }


    @PutMapping("/{codigo}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void actualizarPropiedadAtivo(@PathVariable Long codigo, @Valid @RequestBody Boolean ativo){
        ResponseEntity<Optional<Pessoa>> pessoaSalva = PesooaService.actualizarPropiedadAtivo(codigo, ativo);
        return ResponseEntity.ok(pessoaSalva);
    }

    */



}
