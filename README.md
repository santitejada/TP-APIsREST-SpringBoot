# API de Gestión de Productos

## Descripción del Proyecto
El proyecto consiste en el desarrollo de una API REST para la gestión de productos, implementada con Spring Boot.
Permite realizar operaciones CRUD (crear, leer, actualizar y eliminar) sobre productos almacenados en una base de datos H2 en memoria.

La aplicación sigue una arquitectura en capas —con módulos de controller, service, repository, model y dto— para mantener un diseño ordenado y escalable.
Implementa validaciones con Jakarta Validation, manejo global de excepciones, y utiliza Lombok para simplificar el código.
Cada endpoint está documentado de forma automática mediante Swagger/OpenAPI 3, lo que facilita las pruebas y la comprensión de la API.

La API permite:
- Crear, listar, actualizar y eliminar productos.
- Validar datos obligatorios como nombre, precio, stock y categoría.
- Consultar productos por ID o por categoría.
- Persistir temporalmente los datos en H2 durante la ejecución.

En conjunto, el proyecto aplica buenas prácticas de desarrollo backend utilizando DTOs, ResponseEntity, Swagger, y un enfoque modular basado en Spring Boot.

---

## Tecnologías Utilizadas

- **Java 21** 
- **Spring Boot 3.5.7** 
- **Spring Data JPA** 
- **H2 Database** 
- **Swagger / OpenAPI 3** 
- **Lombok** 
- **Maven** 

---

## Instrucciones para Clonar y Ejecutar

1. Clonar el repositorio:
   ```bash
   git clone https://github.com/santiejada/TP-APIsREST-Productos.git
2. Abrir el proyecto en IntelliJ IDEA o cualquier IDE compatible con Spring Boot.  

3. Compilar y ejecutar la clase principal:  
ProductosApiApplication

5. Abrir en el navegador:    
- Swagger UI: http://localhost:8080/swagger-ui.html  
- API Docs: http://localhost:8080/v3/api-docs  
- Consola H2: http://localhost:8080/h2-console  
JDBC URL: jdbc:h2:mem:productosdb  
Usuario: sa  
Contraseña: (vacía)

---

## Tabla de Endpoints

| Método     | Ruta                                   | Descripción                                        |
| ---------- | -------------------------------------- | -------------------------------------------------- |
| **GET**    | `/api/productos`                       | Lista todos los productos                          |
| **GET**    | `/api/productos/{id}`                  | Busca un producto por su ID                        |
| **GET**    | `/api/productos/categoria/{categoria}` | Lista productos filtrados por categoría            |
| **POST**   | `/api/productos`                       | Crea un nuevo producto                             |
| **PUT**    | `/api/productos/{id}`                  | Actualiza todos los datos de un producto existente |
| **PATCH**  | `/api/productos/{id}/stock`            | Actualiza únicamente el stock de un producto       |
| **DELETE** | `/api/productos/{id}`                  | Elimina un producto por ID                         |

---

## Capturas de Pantalla (Swagger UI y H2)  
- Documentación completa de endpoints  
<img width="645" height="442" alt="image" src="https://github.com/user-attachments/assets/12e1eb30-5afa-4c73-95dd-c18b42cc6bcd" />
   
- Prueba exitosa de POST (Creando producto)  
<img width="886" height="247" alt="image" src="https://github.com/user-attachments/assets/5b1102bf-2120-4749-a722-47156602a96b" />   

- Prueba de GET (Listando productos)  
<img width="886" height="453" alt="image" src="https://github.com/user-attachments/assets/b87136bb-568d-44df-9e6a-2cf656037747" />    

- Error 404 (Producto no existe)  
<img width="886" height="425" alt="image" src="https://github.com/user-attachments/assets/b8ba9d77-80e6-4c61-a8e0-4411d2921a55" />   

- Consola H2 con datos persistidos  
<img width="886" height="454" alt="image" src="https://github.com/user-attachments/assets/cd309013-237f-480f-8554-c59081d991d0" />   

---

## Instrucciones para acceder a Swagger y consola H2

### Swagger UI:
Permite probar cada endpoint desde el navegador de forma interactiva.
URL → http://localhost:8080/swagger-ui.html

### Consola H2:
Permite verificar la base de datos en memoria con consultas SQL.
URL → http://localhost:8080/h2-console
JDBC URL → jdbc:h2:mem:productosdb
User → sa
Password → (vacía)

---

## Conclusiones personales

Durante el desarrollo del trabajo aprendí a:
Implementar una API REST con Spring Boot utilizando buenas prácticas.  
Manejar validaciones y respuestas HTTP adecuadas (200, 201, 400, 404, 204).  
Documentar el proyecto con Swagger/OpenAPI para facilitar las pruebas.  
Comprender cómo funciona la persistencia en memoria con H2.  
Organizar un proyecto con control de versiones usando Git y GitHub.  

## Nombre y legajo 
## Santiago Ariel Tejada - 50181
