🏬 API Backend - Sistema de Bodega

Bienvenido al repositorio del proyecto API Backend - Sistema de Bodega, una API RESTful desarrollada con Spring Boot y Spring Data JPA, orientada a la gestión de inventario, productos, proveedores, ventas y más dentro de una bodega moderna.

Este proyecto sigue buenas prácticas de arquitectura en capas, está documentado con Swagger/OpenAPI y cuenta con validaciones robustas, lo que lo hace ideal tanto para desarrollo profesional como educativo.
🚀 Características Principales

    CRUD completo para productos, proveedores, categorías y ventas.

    Relaciones bien estructuradas entre entidades (por ejemplo, productos dentro de ventas).

    Validación de datos con anotaciones como @NotBlank, @Size, etc.

    Uso de records y MapStruct para mapeo limpio entre entidades y DTOs.

    Arquitectura organizada en capas: controller, service, repository, dto, config, exception, utils, advice, annotation.

    Persistencia con PostgreSQL usando Spring Data JPA.

    Documentación automática con OpenAPI (Swagger).

    Manejo centralizado de errores con @ControllerAdvice.

    Inyección de dependencias y uso de @Builder con Lombok.

    Logging estructurado para seguimiento de peticiones y errores.

🔧 Tecnologías Usadas

    Java 17
    Lenguaje moderno, robusto y seguro. Soporte completo para programación orientada a objetos.

    Spring Boot
    Framework para crear aplicaciones backend de forma rápida y con configuración mínima.

    Spring Data JPA
    Abstracción sobre JPA que facilita la persistencia de datos con repositorios automáticos.

    PostgreSQL
    Base de datos relacional, potente y open source, ideal para aplicaciones empresariales.

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
