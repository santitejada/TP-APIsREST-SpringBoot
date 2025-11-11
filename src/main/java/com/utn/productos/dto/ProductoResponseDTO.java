package com.utn.productos.dto;

import com.utn.productos.model.Categoria;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ProductoResponseDTO {

    @Schema(description = "Identificador único del producto", example = "1")
    private Long id;

    @Schema(description = "Nombre del producto", example = "Teclado mecánico")
    private String nombre;

    @Schema(description = "Descripción del producto", example = "Teclado mecánico con switches rojos")
    private String descripcion;

    @Schema(description = "Precio actual del producto", example = "24999.99")
    private Double precio;

    @Schema(description = "Cantidad disponible en stock", example = "25")
    private Integer stock;

    @Schema(description = "Categoría del producto", example = "ELECTRONICA")
    private Categoria categoria;
}

