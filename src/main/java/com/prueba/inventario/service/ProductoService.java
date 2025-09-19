package com.prueba.inventario.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.prueba.inventario.model.Producto;
import com.prueba.inventario.repository.ProductoRepository;

@Service
public class ProductoService {

    private final ProductoRepository repository;
    private static final Logger logger = LoggerFactory.getLogger(ProductoService.class);

    public ProductoService(ProductoRepository repository) {
        this.repository = repository;
    }

    public List<Producto> findAll() {
        logger.info("Obteniendo todos los productos");
        return repository.findAll();
    }

    public Producto findById(Long id) {
        logger.info("Buscando producto con ID {}", id);
        return repository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Producto con ID {} no encontrado", id);
                    return new RuntimeException("Producto no encontrado");
                });
    }

    public Producto save(Producto producto) {
        logger.info("Guardando nuevo producto: {}", producto.getNombre());
        return repository.save(producto);
    }

    public Producto update(Long id, Producto producto){
        logger.info("Actualizando producto con ID {}", id);
        Producto existing = findById(id);
        existing.setNombre(producto.getNombre());
        existing.setDescripcion(producto.getDescripcion());
        existing.setStock(producto.getStock());
        existing.setPrecio(producto.getPrecio());
        return repository.save(existing);
    }

    public void delete(Long id){
        logger.warn("Eliminando producto con ID {}", id);
        repository.deleteById(id);
    }

    public boolean hayStock(Long id) {
        Producto producto = findById(id);
        boolean disponible = producto.getStock() > 0;
        logger.info("Verificación de stock para producto con ID {}: {}", id, disponible);
        return disponible;
    }

    public Producto entradaStock(Long id, int cantidad) {
        logger.info("Añadiendo {} unidades al producto con ID {}", cantidad, id);
        Producto producto = findById(id);
        producto.setStock(producto.getStock() + cantidad);
        return repository.save(producto);
    }

    public Producto salidaStock(Long id, int cantidad) {
        logger.info("Restando {} unidades al producto con ID {}", cantidad, id);
        Producto producto = findById(id);
        if (producto.getStock() < cantidad) {
            logger.error("Stock insuficiente para producto con ID {}. Stock actual: {}, solicitado: {}",
                         id, producto.getStock(), cantidad);
            throw new RuntimeException("Stock insuficiente");
        }
        producto.setStock(producto.getStock() - cantidad);
        return repository.save(producto);
    }
}
