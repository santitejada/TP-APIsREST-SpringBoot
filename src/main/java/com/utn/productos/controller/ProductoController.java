package com.utn.productos.controller;

import com.utn.productos.dto.ActualizarStockDTO;
import com.utn.productos.dto.ProductoDTO;
import com.utn.productos.dto.ProductoResponseDTO;
import com.utn.productos.exception.ProductoNotFoundException;
import com.utn.productos.model.Categoria;
import com.utn.productos.model.Producto;
import com.utn.productos.service.ProductoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    // =====================================================
    // 1️⃣ Listar todos los productos
    // =====================================================
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
    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> obtenerPorId(@PathVariable Long id) {
        Producto producto = productoService.obtenerPorId(id)
                .orElseThrow(() -> new ProductoNotFoundException(id));
        return ResponseEntity.ok(convertirAResponseDTO(producto));
    }

    // =====================================================
    // 3️⃣ Filtrar por categoría
    // =====================================================
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
    @PostMapping
    public ResponseEntity<ProductoResponseDTO> crearProducto(@Valid @RequestBody ProductoDTO dto) {
        Producto producto = convertirADominio(dto);
        Producto guardado = productoService.crearProducto(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertirAResponseDTO(guardado));
    }

    // =====================================================
    // 5️⃣ Actualizar producto completo
    // =====================================================
    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> actualizarProducto(
            @PathVariable Long id,
            @Valid @RequestBody ProductoDTO dto) {

        Producto productoActualizado = productoService.actualizarProducto(id, convertirADominio(dto));
        return ResponseEntity.ok(convertirAResponseDTO(productoActualizado));
    }

    // =====================================================
    // 6️⃣ Actualizar solo el stock (PATCH)
    // =====================================================
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
