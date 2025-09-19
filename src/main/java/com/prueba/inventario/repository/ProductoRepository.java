package com.prueba.inventario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prueba.inventario.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    
}
