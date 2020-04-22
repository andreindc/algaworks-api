package com.algaworksapi.algaworksapi.resource;

import com.algaworksapi.algaworksapi.event.RecursoCriadoEvent;
import com.algaworksapi.algaworksapi.exceptionhandler.AlgamoneyExceptionHandler;
import com.algaworksapi.algaworksapi.model.Lancamento;
import com.algaworksapi.algaworksapi.model.Pessoa;
import com.algaworksapi.algaworksapi.repository.LancamentoRepository;
import com.algaworksapi.algaworksapi.repository.PessoaRepository;
import com.algaworksapi.algaworksapi.service.LancamentoService;
import com.algaworksapi.algaworksapi.service.exception.PessoaInexistenteOuInativaException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {
    @Autowired
    private LancamentoRepository lancamentoRepository;

    @Autowired
    private LancamentoService lancamentoService;

    @Autowired
    private MessageSource messageSource;

    @GetMapping
    public List<Lancamento> listar(){
        return lancamentoRepository.findAll();
    }

    @Autowired //Disparando un evento de aplicación, necesito validar que no este duplicado
    private ApplicationEventPublisher publisher;

    @PostMapping
    public ResponseEntity<Lancamento> criar(@Valid @RequestBody Lancamento lancamento, HttpServletResponse response){
        Lancamento  lancamentoSalva = lancamentoService.salvar(lancamento);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, lancamentoSalva.getCodigo()));
        return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoSalva);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Optional<Lancamento>> buscarPeloCodigo(@PathVariable Long codigo){
        Optional<Lancamento> lancamento = lancamentoRepository.findById(codigo);
        return lancamento != null ? ResponseEntity.ok(lancamento) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long codigo){
        lancamentoRepository.deleteById(codigo);
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Lancamento> actualizar(@PathVariable Long codigo, @Valid @RequestBody Lancamento lancamento){
        Optional<Lancamento> lancamentoSalva = lancamentoRepository.findById(codigo);
        BeanUtils.copyProperties(lancamento,lancamentoSalva,"codigo");
        lancamento.setCodigo(codigo);
        lancamentoRepository.save(lancamento);
        return ResponseEntity.ok(lancamento);
    }

    @ExceptionHandler({PessoaInexistenteOuInativaException.class})
    public ResponseEntity<Object> handlePessoaInexistenteOuInativaException(PessoaInexistenteOuInativaException ex){
        String mensagemUsuario = messageSource.getMessage("pessoa.inexistente-ou-inativa", null, LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ex.toString();
        List<AlgamoneyExceptionHandler.Erro> erros = Arrays.asList(new AlgamoneyExceptionHandler.Erro(mensagemUsuario, mensagemDesenvolvedor));
        return  ResponseEntity.badRequest().body(erros);
    }

}
