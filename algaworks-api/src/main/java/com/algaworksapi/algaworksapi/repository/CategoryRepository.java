package com.algaworksapi.algaworksapi.repository;

import com.algaworksapi.algaworksapi.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryRepository extends JpaRepository<Categoria, Long> { //Tabla y tipo de dato de la clave primaria
}
