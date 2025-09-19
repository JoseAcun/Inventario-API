package com.prueba.inventario.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prueba.inventario.model.Producto;
import com.prueba.inventario.service.ProductoService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(ProductoController.class)
public class ProductoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductoService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCrearProducto() throws Exception {
        Producto producto = new Producto(1L, "Laptop", "", 10, 2500.0);

        when(service.save(any(Producto.class))).thenReturn(producto);

        mockMvc.perform(post("/api/productos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(producto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Laptop"))
                .andExpect(jsonPath("$.stock").value(10));
    }

    @Test
    void testGetPoductById() throws Exception {
        Producto producto = new Producto(1L, "Laptop","" , 10, 2500.0);

        when(service.findById(1L)).thenReturn(producto);

        mockMvc.perform(get("/api/productos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Laptop"))
                .andExpect(jsonPath("$.precio").value(2500.0));
    }
    
}
