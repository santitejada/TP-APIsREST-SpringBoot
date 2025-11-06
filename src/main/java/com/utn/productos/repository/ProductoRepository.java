package com.utn.productos.repository;

import com.utn.productos.model.Categoria;
import com.utn.productos.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    // Método personalizado para buscar por categoría
    List<Producto> findByCategoria(Categoria categoria);
}
