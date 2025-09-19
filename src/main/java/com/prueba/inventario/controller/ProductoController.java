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




@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService service;

    public ProductoController(ProductoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Producto> getAll() {
        return service.findAll();
    }
    
    @GetMapping("/{id}")
    public Producto getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public Producto create(@RequestBody Producto producto) {
        return service.save(producto);
    }

    @PutMapping("/{id}")
    public Producto update(@PathVariable Long id, @RequestBody Producto producto) {       
        return service.update(id, producto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
    
    @GetMapping("/{id}/stock")
    public boolean hayStock(@PathVariable Long id) {
        return service.hayStock(id);
    }

    @PostMapping("/{id}/entrada/{cantidad}")
    public Producto entrada(@PathVariable Long id, @PathVariable int cantidad) {   
        return service.entradaStock(id, cantidad);
    }

    @PostMapping("/{id}/salida/{cantidad}")
    public Producto salida(@PathVariable Long id, @PathVariable int cantidad) {    
        return service.salidaStock(id, cantidad);
    }
    
    
    
}
