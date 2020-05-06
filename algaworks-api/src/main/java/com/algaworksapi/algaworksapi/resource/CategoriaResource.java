package com.algaworksapi.algaworksapi.resource;

import com.algaworksapi.algaworksapi.event.RecursoCriadoEvent;
import com.algaworksapi.algaworksapi.model.Categoria;
import com.algaworksapi.algaworksapi.model.Pessoa;
import com.algaworksapi.algaworksapi.repository.CategoryRepository;
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
@RequestMapping("/categorias") //Estoy mapeando la requisición

public class CategoriaResource {

    @Autowired //Encuentra una implementación de categoria repositorio y la inyecta en la variable
    private CategoryRepository categoriaRepository;


    //	@CrossOrigin(maxAge = 10, origins = { "http://localhost:8000" })
    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_CATEGORIA') and #oauth2.hasScope('read')")
    public List<Categoria> listar(){
        return categoriaRepository.findAll(); //Metodo de la implementación
    }

    @Autowired //Disparando un evento de aplicación, necesito validar que no este duplicado
    private ApplicationEventPublisher publisher;


    // @ResponseStatus(HttpStatus.CREATED) Al terminar retorna un codigo de creado 201, más con el return ya no hace falta
    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_CATEGORIA') and #oauth2.hasScope('write')")
    //La primera parte es del usuario logueado, la otra es el scopes del cliente (angular, mobile)
    //Não preciso definir essa anotação pois já passo o status no return do response
    //@ResponseStatus(HttpStatus.CREATED)
    //A anotação "@Valid" para que não dê erro 500 no lado do servidor, pois o erro é no lado cliente com o 400.
    public ResponseEntity<Categoria> criar(@Valid @RequestBody Categoria categoria, HttpServletResponse response){
        Categoria categoriaSalva = categoriaRepository.save(categoria);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, categoriaSalva.getCodigo()));

        //Apartir del url de la requisición actual
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);
    }

    @GetMapping("/{codigo}")
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_CATEGORIA') and #oauth2.hasScope('write')")
    public ResponseEntity<Optional<Categoria>> buscarPeloCodigo(@PathVariable Long codigo){
        Optional<Categoria> categoria = categoriaRepository.findById(codigo);
        return categoria != null ? ResponseEntity.ok(categoria) : ResponseEntity.notFound().build();
    }



}
