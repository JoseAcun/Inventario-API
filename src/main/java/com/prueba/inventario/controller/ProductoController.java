package com.prueba.inventario.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.inventario.model.Producto;
import com.prueba.inventario.service.ProductoService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService service;
    private static final Logger logger = LoggerFactory.getLogger(ProductoController.class);

    public ProductoController(ProductoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Producto> getAll() {
        logger.info("Solicitud para obtener todos los productos");
        List<Producto> productos = service.findAll();
        logger.debug("Cantidad de productos encontrados: {}", productos.size());
        return productos;
    }
    
    @GetMapping("/{id}")
    public Producto getById(@PathVariable Long id) {
        logger.info("Solicitud para obtener producto con ID {}", id);
        Producto producto = service.findById(id);
        logger.debug("Producto recuperado: {}", producto);
        return producto;
    }

    @PostMapping
    public Producto create(@RequestBody Producto producto) {
        logger.info("Solicitud para crear producto: {}", producto.getNombre());
        Producto creado = service.save(producto);
        logger.debug("Producto creado con ID {}", creado.getId());
        return creado;
    }

    @PutMapping("/{id}")
    public Producto update(@PathVariable Long id, @RequestBody Producto producto) {
        logger.info("Solicitud para actualizar producto con ID {}", id);
        Producto actualizado = service.update(id, producto);
        logger.debug("Producto actualizado: {}", actualizado);
        return actualizado;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        logger.warn("Solicitud para eliminar producto con ID {}", id);
        service.delete(id);
        logger.info("Producto con ID {} eliminado correctamente", id);
    }
    
    @GetMapping("/{id}/stock")
    public boolean hayStock(@PathVariable Long id) {
        logger.info("Solicitud para verificar stock del producto con ID {}", id);
        boolean disponible = service.hayStock(id);
        logger.debug("Stock disponible: {}", disponible);
        return disponible;
    }

    @PostMapping("/{id}/entrada/{cantidad}")
    public Producto entrada(@PathVariable Long id, @PathVariable int cantidad) {   
        logger.info("Solicitud para registrar entrada de {} unidades al producto con ID {}", cantidad, id);
        Producto actualizado = service.entradaStock(id, cantidad);
        logger.debug("Stock tras entrada: {}", actualizado.getStock());
        return actualizado;
    }

    @PostMapping("/{id}/salida/{cantidad}")
    public Producto salida(@PathVariable Long id, @PathVariable int cantidad) {    
        logger.info("Solicitud para registrar salida de {} unidades del producto con ID {}", cantidad, id);
        Producto actualizado = service.salidaStock(id, cantidad);
        logger.debug("Stock tras salida: {}", actualizado.getStock());
        return actualizado;
    }
}