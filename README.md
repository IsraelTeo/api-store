# 🏬 API Backend - Sistema de Bodega

Bienvenido al repositorio del proyecto **API Backend - Sistema de Bodega**, una API RESTful desarrollada con **Spring Boot** y **R2DBC** para la gestión de inventario, productos, proveedores, ventas y más dentro de una bodega.

Este proyecto está diseñado para facilitar el control y administración de los elementos clave de una operación de bodega moderna, ofreciendo endpoints bien estructurados y listos para integrarse con una aplicación frontend o ser utilizados directamente desde herramientas como Postman.

---

## 🚀 Características Principales

- CRUD completo para productos, proveedores, categorías y ventas.
- Gestión de relaciones entre entidades (por ejemplo, productos en ventas).
- Validaciones de datos con anotaciones como `@NotBlank`, `@Size`, etc.
- Mapeo limpio usando `records` y mappers con `@Builder`.
- Arquitectura en capas (Controller, Service, Repository, DTO, Config, Exception, etc.).
- Conexión reactiva a base de datos **PostgreSQL/MySQL** mediante **Spring Data R2DBC**.
- Manejo de errores centralizado con `@ControllerAdvice`.
- Buenas prácticas de desarrollo y código limpio.

---

## 🔧 Tecnologías Usadas

- **Golang**  
  Lenguaje de programación eficiente, rápido y con concurrencia incorporada, ideal para construir APIs escalables.

- **PostgreSQL**  
  Sistema de gestión de bases de datos relacional, potente y de código abierto, ampliamente usado en aplicaciones empresariales.

- **GORM**  
  ORM (Object Relational Mapping) para Go, que simplifica el trabajo con bases de datos al mapear estructuras de Go a tablas relacionales.

  ## 📚 Librerías

- [`github.com/go-playground/validator/v10`](https://github.com/go-playground/validator)  
  Biblioteca de validación para estructuras en Go. Permite aplicar reglas como `required`, `email`, `min`, `max`, entre otras, directamente en los tags de las estructuras.

- [`github.com/joho/godotenv`](https://github.com/joho/godotenv)  
  Permite cargar variables de entorno desde un archivo `.env`, facilitando la configuración del entorno de desarrollo.

- [`github.com/google/uuid`](https://github.com/google/uuid)  
  Biblioteca oficial de Google para generar identificadores únicos universales (UUID) de manera simple y segura.

- [`golang.org/x/exp/slog`](https://pkg.go.dev/golang.org/x/exp/slog)  
  Logger estructurado experimental de alto rendimiento diseñado para sustituir los `log` clásicos en Go. Facilita el registro jerárquico y configurable.

- [`go.uber.org/fx`](https://github.com/uber-go/fx)  
  Framework de Uber para inyección de dependencias y manejo de ciclo de vida de aplicaciones Go. Promueve la modularidad y configuración declarativa.

- [`github.com/gorilla/mux`](https://github.com/gorilla/mux)  
  Enrutador HTTP poderoso para Go, permite definir rutas con variables, métodos, expresiones regulares y middlewares personalizados.

## Estructura del proyecto
- CRUD completo para productos, proveedores, categorías y ventas.
src/
├── config/            # Configuraciones globales (DB, CORS, etc.)
├── controller/        # Controladores REST
├── dto/               # Objetos de transferencia de datos
├── exception/         # Manejo de errores y excepciones personalizadas
├── mapper/            # Mapeadores entre entidades y DTOs
├── model/             # Entidades del dominio
├── payload/           # Request/Response personalizados
├── repository/        # Interfaces R2DBC para acceso a datos
├── service/           # Lógica de negocio
├── utils/             # Funciones utilitarias
└── Application.java   # Clase principal del proyecto

## 🔧 Endpoints
GET    /api/products
POST   /api/products
PUT    /api/products/{id}
DELETE /api/products/{id}
POST   /api/sales
GET    /api/providers

## 📦 Instalación y Ejecución

## 📦 Instalación y Ejecución

1. **Instala Golang**  
   Asegúrate de tener Go instalado en tu máquina. Puedes descargarlo desde el sitio oficial:  
   👉 [https://golang.org/dl/](https://golang.org/dl/)

2. **Clona el repositorio**  
   ```bash
   git clone https://github.com/tu-usuario/api-bodega.git
   cd api-bodega
2. **Crea el archivo .env en la raíz del proyecto**
  ```bash
  PORT=8080
  DB_USER=root
  DB_PASSWORD=bluZcvSrFtGhctGZnibdZocxcPTcEJJt
  DB_HOST=hopper.proxy.rlwy.net
  DB_PORT=43126
  DB_NAME=railway
  JWT_EXP=86400
  API_SECRET=3ee92e2e-209f-47f9-8b7c-07ef2855ca3c

2. **Crea el archivo .env en la raíz del proyecto**
  ```bash
  go run main.go
