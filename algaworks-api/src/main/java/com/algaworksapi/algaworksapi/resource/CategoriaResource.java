package com.algaworksapi.algaworksapi.resource;

import com.algaworksapi.algaworksapi.event.RecursoCriadoEvent;
import com.algaworksapi.algaworksapi.model.Categoria;
import com.algaworksapi.algaworksapi.repository.CategoryRepository;
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
@RequestMapping("/categorias") //Estoy mapeando la requisición

public class CategoriaResource {

    @Autowired //Encuentra una implementación de categoria repositorio y la inyecta en la variable
    private CategoryRepository categoriaRepository;

    @GetMapping
    public List<Categoria> listar(){
        return categoriaRepository.findAll(); //Metodo de la implementación
    }

    @Autowired //Disparando un evento de aplicación, necesito validar que no este duplicado
    private ApplicationEventPublisher publisher;


    // @ResponseStatus(HttpStatus.CREATED) Al terminar retorna un codigo de creado 201, más con el return ya no hace falta
    @PostMapping                            //El valid valida que no se null el campo
    public ResponseEntity<Categoria> criar(@Valid @RequestBody Categoria categoria, HttpServletResponse response){
        Categoria categoriaSalva = categoriaRepository.save(categoria);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, categoriaSalva.getCodigo()));

        //Apartir del url de la requisición actual
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);
    }

    @GetMapping("/{codigo}")
    public Optional<Categoria> buscarPeloCodigo(@PathVariable Long codigo){
        return categoriaRepository.findById(codigo);
    }



}
