API Backend - Sistema de Bodega

Una API RESTful orientada a la gestión de una bodega.

Este proyecto contiene:

    CRUD completo para productos, clientes y ventas.

    Relaciones bien estructuradas entre entidades (por ejemplo, cliete y productos dentro de ventas).

**Tecnologías Usadas**

    Java 17
     Lenguaje de programación orientado a objetos, robusto y ampliamente utilizado en el desarrollo empresarial por su seguridad.

    Spring Boot
    Framework para crear aplicaciones backend de forma rápida y con configuración mínima.

    Spring Data JPA
    Módulo de Spring Framework que funciona como una abstracción sobre JPA para facilitar la persistencia 
    de datos.

    PostgreSQL
    Base de datos relacional ideal para aplicaciones empresariales.

    Lombok
    Elimina código repetitivo como getters, setters, builders, etc., mediante anotaciones.

    MapStruct
    Framework para el mapeo automático entre entidades y DTOs, basado en interfaces.

    Spring Validation
    Validación declarativa de campos en DTOs usando anotaciones.

    Swagger / OpenAPI
    Documentación interactiva de endpoints REST directamente desde el código.

    SLF4J + Logback
    Logging flexible y personalizable para auditoría y depuración.

 Buenas prácticas

    Uso de archivos .yml para centralizar configuraciones como puertos, credenciales de base de datos, etc.

    Inyección de dependencias a través de constructores utilizando Lombok (@RequiredArgsConstructor) 
    para evitar acoplamiento directo con el framework y facilitar las futuras pruebas unitarias.

    Manejo centralizado de excepciones.

    Documentación automática y actualizada de los endpoints REST con Swagger / OpenAPI.

    Uso de DTOs para transferir datos entre cliente y servidor.

    Validación de datos de entrada en los DTOs utilizando anotaciones como @NotBlank, @Size, 
    @Valid, entre otras.

    Implementación de paginación en endpoints que devuelven listas límitadas de datos para 
    mejorar rendimiento y escalabilidad.

    Arquitectura de capas bien definida: controller, service, repository, dto, mapper, 
    exception, domain, mapper, criteria.

    Uso de nombres descriptivos para variables, constantes, métodos, clases, paquetes 
    e interfaces.

    Aplicación del principio de responsabilidad única (SRP) del conjunto SOLID para mantener 
    clases y métodos mantenibles.

    Mapeos limpios y desacoplados entre entidades y DTOs utilizando MapStruct.

    Logging estructurado con SLF4J y Logback para monitorear el flujo y los errores.

    
