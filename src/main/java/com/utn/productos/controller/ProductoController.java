package com.utn.productos.controller;

import com.utn.productos.dto.ActualizarStockDTO;
import com.utn.productos.dto.ProductoDTO;
import com.utn.productos.dto.ProductoResponseDTO;
import com.utn.productos.exception.ProductoNotFoundException;
import com.utn.productos.model.Categoria;
import com.utn.productos.model.Producto;
import com.utn.productos.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Productos", description = "Operaciones CRUD para la gestión de productos")
@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    // =====================================================
    // 1️⃣ Listar todos los productos
    // =====================================================
    @Operation(summary = "Listar todos los productos",
            description = "Devuelve una lista con todos los productos almacenados.")
    @ApiResponse(responseCode = "200", description = "Lista de productos devuelta correctamente")
    @GetMapping
    public ResponseEntity<List<ProductoResponseDTO>> obtenerTodos() {
        List<ProductoResponseDTO> productos = productoService.obtenerTodos()
                .stream()
                .map(this::convertirAResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(productos);
    }

    // =====================================================
    // 2️⃣ Obtener producto por ID
    // =====================================================
    @Operation(summary = "Obtener producto por ID",
            description = "Devuelve un producto si existe el ID especificado.")
    @ApiResponse(responseCode = "200", description = "Producto encontrado")
    @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> obtenerPorId(@PathVariable Long id) {
        Producto producto = productoService.obtenerPorId(id)
                .orElseThrow(() -> new ProductoNotFoundException(id));
        return ResponseEntity.ok(convertirAResponseDTO(producto));
    }

    // =====================================================
    // 3️⃣ Filtrar por categoría
    // =====================================================
    @Operation(summary = "Filtrar productos por categoría",
            description = "Devuelve una lista de productos que pertenecen a una categoría dada.")
    @ApiResponse(responseCode = "200", description = "Lista filtrada devuelta correctamente")
    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<ProductoResponseDTO>> obtenerPorCategoria(@PathVariable Categoria categoria) {
        List<ProductoResponseDTO> productos = productoService.obtenerPorCategoria(categoria)
                .stream()
                .map(this::convertirAResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(productos);
    }

    // =====================================================
    // 4️⃣ Crear producto
    // =====================================================
    @Operation(summary = "Crear un nuevo producto",
            description = "Crea un nuevo producto en base a los datos enviados en el cuerpo del request.")
    @ApiResponse(responseCode = "201", description = "Producto creado exitosamente")
    @ApiResponse(responseCode = "400", description = "Error de validación en los datos del producto")
    @PostMapping
    public ResponseEntity<ProductoResponseDTO> crearProducto(@Valid @RequestBody ProductoDTO dto) {
        Producto producto = convertirADominio(dto);
        Producto guardado = productoService.crearProducto(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertirAResponseDTO(guardado));
    }

    // =====================================================
    // 5️⃣ Actualizar producto completo
    // =====================================================
    @Operation(summary = "Actualizar un producto completo",
            description = "Actualiza todos los campos de un producto existente.")
    @ApiResponse(responseCode = "200", description = "Producto actualizado correctamente")
    @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> actualizarProducto(
            @PathVariable Long id,
            @Valid @RequestBody ProductoDTO dto) {

        Producto productoActualizado = productoService.actualizarProducto(id, convertirADominio(dto));
        return ResponseEntity.ok(convertirAResponseDTO(productoActualizado));
    }

    // =====================================================
    // 6️⃣ Actualizar solo el stock
    // =====================================================
    @Operation(summary = "Actualizar solo el stock de un producto",
            description = "Modifica únicamente el valor del stock de un producto existente.")
    @ApiResponse(responseCode = "200", description = "Stock actualizado correctamente")
    @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    @ApiResponse(responseCode = "400", description = "Valor de stock inválido")
    @PatchMapping("/{id}/stock")
    public ResponseEntity<ProductoResponseDTO> actualizarStock(
            @PathVariable Long id,
            @Valid @RequestBody ActualizarStockDTO dto) {

        Producto productoActualizado = productoService.actualizarStock(id, dto.getStock());
        return ResponseEntity.ok(convertirAResponseDTO(productoActualizado));
    }

    // =====================================================
    // 7️⃣ Eliminar producto
    // =====================================================
    @Operation(summary = "Eliminar un producto",
            description = "Elimina un producto del sistema si existe el ID especificado.")
    @ApiResponse(responseCode = "204", description = "Producto eliminado exitosamente")
    @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }

    // =====================================================
    // Métodos auxiliares para conversión
    // =====================================================
    private ProductoResponseDTO convertirAResponseDTO(Producto producto) {
        ProductoResponseDTO dto = new ProductoResponseDTO();
        dto.setId(producto.getId());
        dto.setNombre(producto.getNombre());
        dto.setDescripcion(producto.getDescripcion());
        dto.setPrecio(producto.getPrecio());
        dto.setStock(producto.getStock());
        dto.setCategoria(producto.getCategoria());
        return dto;
    }

    private Producto convertirADominio(ProductoDTO dto) {
        Producto producto = new Producto();
        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setPrecio(dto.getPrecio());
        producto.setStock(dto.getStock());
        producto.setCategoria(dto.getCategoria());
        return producto;
    }
}
