package com.utn.productos.dto;

import com.utn.productos.model.Categoria;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ProductoDTO {

    @Schema(description = "Nombre del producto", example = "Mouse gamer RGB", required = true)
    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    private String nombre;

    @Schema(description = "Descripción detallada del producto", example = "Mouse con 6 botones programables y luces RGB")
    @Size(max = 500, message = "La descripción no puede superar los 500 caracteres")
    private String descripcion;

    @Schema(description = "Precio del producto", example = "5999.99", required = true)
    @NotNull(message = "El precio no puede ser nulo")
    @DecimalMin(value = "0.01", message = "El precio debe ser mayor que 0")
    private Double precio;

    @Schema(description = "Cantidad disponible en stock", example = "15", required = true)
    @NotNull(message = "El stock no puede ser nulo")
    @Min(value = 0, message = "El stock no puede ser negativo")
    private Integer stock;

    @Schema(description = "Categoría del producto", example = "ELECTRONICA", required = true)
    @NotNull(message = "La categoría no puede ser nula")
    private Categoria categoria;
}
