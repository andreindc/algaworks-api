package com.algaworksapi.algaworksapi.resource;

import com.algaworksapi.algaworksapi.model.Categoria;
import com.algaworksapi.algaworksapi.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categorias") //Estoy mapeando la requisición

public class CategoriaResourse {

    @Autowired //Encuentra una implementación de categoria repositorio y la inyecta en la variable
    private CategoryRepository categoriaRepository;

    @GetMapping
    public List<Categoria> listar(){
        return categoriaRepository.findAll(); //Metodo de la implementación
    }

    // @ResponseStatus(HttpStatus.CREATED) Al terminar retorna un codigo de creado 201, más con el return ya no hace falta
    @PostMapping
    public ResponseEntity<Categoria> criar(@RequestBody Categoria categoria, HttpServletResponse response){
        Categoria categoriaSalva = categoriaRepository.save(categoria);

        URI url = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}").buildAndExpand(categoriaSalva.getCodigo()).toUri();
        response.setHeader("Location", url.toASCIIString());
        //Apartir del url de la requisición actual
        return ResponseEntity.created(url).body(categoriaSalva);
    }

    @GetMapping("/{codigo}")
    public Optional<Categoria> buscarPeloCodigo(@PathVariable Long codigo){
        return categoriaRepository.findById(codigo);
    }



}
