# Servicio de Precios de Zara

Este proyecto es un servicio REST desarrollado en Spring Boot que permite consultar precios aplicables para productos de la marca Zara en un rango de fechas determinado. Utiliza una base de datos en memoria H2 y está diseñado para manejar solicitudes con parámetros de fecha de aplicación, identificador de producto y identificador de cadena.

## Tabla de Contenidos

- [Descripción del Proyecto](#descripción-del-proyecto)
- [Tecnologías Utilizadas](#tecnologías-utilizadas)
- [Estructura del Proyecto](#estructura-del-proyecto)
- [Configuración del Proyecto](#configuración-del-proyecto)
- [Uso](#uso)
- [Ejemplos de Peticiones](#ejemplos-de-peticiones)
- [Pruebas](#pruebas)
- [Mejoras Futuras](#mejoras-futuras)
- [Contribuciones](#contribuciones)
- [Licencia](#licencia)

## Descripción del Proyecto

La aplicación proporciona un endpoint REST que acepta los siguientes parámetros:

- **fecha de aplicación**: La fecha y hora para la cual se desea consultar el precio.
- **identificador de producto**: El ID del producto cuyo precio se quiere consultar.
- **identificador de cadena**: El ID de la cadena (en este caso, 1 para Zara).

La aplicación devuelve el identificador de producto, identificador de cadena, tarifa a aplicar, fechas de aplicación y precio final.

### Tabla de Precios

La base de datos incluye la tabla **PRICES** con los siguientes campos:

- **BRAND_ID**: foreign key de la cadena del grupo (1 = ZARA).
- **START_DATE**: fecha de inicio de la tarifa.
- **END_DATE**: fecha de fin de la tarifa.
- **PRICE_LIST**: identificador de la tarifa de precios aplicable.
- **PRODUCT_ID**: identificador del producto.
- **PRIORITY**: desambiguador de aplicación de precios (mayor valor numérico indica mayor prioridad).
- **PRICE**: precio final de venta.
- **CURR**: código ISO de la moneda (EUR).

## Tecnologías Utilizadas

- Java 21
- Spring Boot 3.3.4
- Spring Data JPA
- H2 Database (base de datos en memoria)
- JUnit 5 (para pruebas)

## Estructura del Proyecto

```plaintext
src
├── main
│   ├── java
│   │   └── com
│   │       └── zara
│   │           └── alf
│   │               ├── application
│   │               ├── infrastructure
│   │               └── domain
│   └── resources
├── test
│   └── java
│       └── com
│           └── zara
│               └── alf

```

## Mejoras Futuras

- **Swagger**: Implementar Swagger para una documentación interactiva de la API.
- **DTOs**: Introducir Data Transfer Objects para un manejo más claro de las respuestas de la API.
- **Manejo de Excepciones**: Añadir un sistema centralizado de manejo de excepciones.
- **Pruebas Unitarias y de Integración**: Mejorar la cobertura de pruebas.
- **Configuración de Seguridad**: Implementar medidas de seguridad como autenticación y autorización.


## Contribuciones

- **ALF**