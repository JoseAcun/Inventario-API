package com.prueba.inventario.service;

import com.prueba.inventario.model.Producto;
import com.prueba.inventario.repository.ProductoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductoServiceTest {

    @Mock
    private ProductoRepository repository;

    @InjectMocks
    private ProductoService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGuardarProducto() {
        Producto producto = new Producto(null, "Laptop", "Laptop gamer", 10, 2500.0);

        when(repository.save(producto)).thenReturn(new Producto(1L, "Laptop", "", 10, 2500.0));

        Producto resultado = service.save(producto);

        assertNotNull(resultado.getId());
        assertEquals("Laptop", resultado.getNombre());
        verify(repository, times(1)).save(producto);
    }

    @Test
    void testHayStock() {
        Producto producto = new Producto(1L, "Laptop", "laptop gamer", 5, 2000.0);
        when(repository.findById(1L)).thenReturn(Optional.of(producto));

        boolean Stock = service.hayStock(1L);

        assertTrue(Stock);
        verify(repository, times(1)).findById(1L);
    }
    
}
