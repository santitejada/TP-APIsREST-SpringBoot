package com.utn.productos.dto;

import com.utn.productos.model.Categoria;
import lombok.Data;

@Data
public class ProductoResponseDTO {

    private Long id;
    private String nombre;
    private String descripcion;
    private Double precio;
    private Integer stock;
    private Categoria categoria;
}
