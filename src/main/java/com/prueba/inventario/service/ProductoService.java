package com.prueba.inventario.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.prueba.inventario.model.Producto;
import com.prueba.inventario.repository.ProductoRepository;

@Service
public class ProductoService {

    private final ProductoRepository repository;

    public ProductoService(ProductoRepository repository) {
        this.repository = repository;
    }

    public List<Producto> findAll() {
        return repository.findAll();
    }

    public Producto findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    public Producto save(Producto producto) {
        return repository.save(producto);
    }

    public Producto update(Long id, Producto producto){
        Producto existing = findById(id);
        existing.setNombre(producto.getNombre());
        existing.setDescripcion(producto.getDescripcion());
        existing.setStock(producto.getStock());
        existing.setPrecio(producto.getPrecio());
        return repository.save(existing);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    public boolean hayStock(Long id) {
        Producto producto = findById(id);
        return producto.getStock() > 0;
    }

    public Producto entradaStock(Long id, int cantidad) {
    Producto producto = findById(id);
    producto.setStock(producto.getStock() + cantidad);
    return repository.save(producto);
    }

    public Producto salidaStock(Long id, int cantidad) {
        Producto producto = findById(id);
        if (producto.getStock() < cantidad) {
            throw new RuntimeException("Stock insuficiente");
        }
        producto.setStock(producto.getStock() - cantidad);
        return repository.save(producto);
    }
    
}
