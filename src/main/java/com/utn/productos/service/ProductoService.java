package com.utn.productos.service;

import com.utn.productos.model.Categoria;
import com.utn.productos.model.Producto;
import com.utn.productos.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    // Inyección por constructor (recomendada)
    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    // ==============================
    // MÉTODOS CRUD PRINCIPALES
    // ==============================

    // Crear un nuevo producto
    public Producto crearProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    // Obtener todos los productos
    public List<Producto> obtenerTodos() {
        return productoRepository.findAll();
    }

    // Obtener producto por ID
    public Optional<Producto> obtenerPorId(Long id) {
        return productoRepository.findById(id);
    }

    // Obtener productos por categoría
    public List<Producto> obtenerPorCategoria(Categoria categoria) {
        return productoRepository.findByCategoria(categoria);
    }

    // Actualizar un producto completo
    public Producto actualizarProducto(Long id, Producto productoActualizado) {
        Optional<Producto> optionalProducto = productoRepository.findById(id);

        if (optionalProducto.isEmpty()) {
            // Esta excepción la vamos a crear en la Parte 5
            throw new RuntimeException("Producto no encontrado con ID: " + id);
        }

        Producto productoExistente = optionalProducto.get();

        productoExistente.setNombre(productoActualizado.getNombre());
        productoExistente.setDescripcion(productoActualizado.getDescripcion());
        productoExistente.setPrecio(productoActualizado.getPrecio());
        productoExistente.setStock(productoActualizado.getStock());
        productoExistente.setCategoria(productoActualizado.getCategoria());

        return productoRepository.save(productoExistente);
    }

    // Actualizar solo el stock
    public Producto actualizarStock(Long id, Integer nuevoStock) {
        Optional<Producto> optionalProducto = productoRepository.findById(id);

        if (optionalProducto.isEmpty()) {
            throw new RuntimeException("Producto no encontrado con ID: " + id);
        }

        Producto producto = optionalProducto.get();
        producto.setStock(nuevoStock);

        return productoRepository.save(producto);
    }

    // Eliminar producto
    public void eliminarProducto(Long id) {
        if (!productoRepository.existsById(id)) {
            throw new RuntimeException("Producto no encontrado con ID: " + id);
        }
        productoRepository.deleteById(id);
    }
}
