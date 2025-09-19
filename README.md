# Inventario API

Esta es una API REST de gestion d einventario construida con **Spring Boot**, con persistencia en **MariaDB** y construido con **Gradle**.
Permite la gestion basica de productos en inventario como cracion, consulta, actualizacion, eliminacion y control de stock.


## Tecnologias

- **Java 21**
- **Spring Boot 3**
- **Spring Data JPA**
- **MariaDB**
- **Gradle**
- **Lombok**
- **SpringDoc OpenAPI (Swagger UI)**


## Requisitos 

Para correr la API debe:

- **JDK 21**
tener MariaDB corriendo en el puerto 3306 o configurarlo previamente en application.properties y crear una base de datos "inventario"

**CREATE** DATABASE inventario;

## application.properties(src/main/resources/application.properties)

spring.application.name=inventario

spring.datasource.url=jdbc:mariadb://localhost:__3306__/inventario **Eleccion de motor de base de datos montado en el pueto 3306**

spring.datasource.username=root  **Usuario para acceder a bases de datos**

spring.datasource.password=password **Reemplazar por contraseña para acceder a las bases de datos**

spring.datasource.driver-class-name=org.mariadb.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MariaDBDialect

server.port=8080 **Puerto en el que corre la API**

## Correr la API

Despues de clonar el repositorio desde la consola:

**cd inventario/**

Construir proyecto:

./gradlew clean build

Ejecutar la aplicacion

./gradlew bootRun

La Api quedara corriendo en http://localhost:8080

## Principales endpoints

**GET** /api/productos -> Lista todos los productos.  
**GET** /api/productos/{id} -> Obtener un producto por ID.  
**POST** /api/productos -> Crear un nuevo producto.  

JSON:
    {
    "nombre": "Laptop",
    "descripcion": "Laptop de 15 pulgadas",
    "stock": 10,
    "precio": 2500.0
    }  

**PUT** /api/productos/{id} -> Actualizar un producto existente.  
**DELETE** /api/productos/{id} -> Eliminar un producto.  

------------------------------------------

## Stock

**GET** /api/productos/{id}/stock -> Consulta si hay stock disponible.  
**POST** /api/productos/{id}/entrada/{cantidad} → Registrar entrada de stock.  
**POST** /api/productos/{id}/salida/{cantidad} → Registrar salida de stock.  

## Documentación interactiva

El proyecto incluye Swagger UI para explorar y probar la API:

**http://localhost:8080/swagger-ui/index.html**

## Pruebas unitarias

El proyecto contiene pruebas unitarias en:

**ProductoServiceTest.java**

**ProductoControllerTest.java**

se ejecutan con:

./gradle test

El reporte se genera en: **/build/reports/test/index.html**

## Autor: **Jose Acuña**