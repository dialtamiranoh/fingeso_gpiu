# GPIU - Gestión de Puntos de Información Universitaria

Sistema de gestión de puntos de información universitaria para Universidad de Santiago de Chile (USACH).

## Descripción

GPIU es una aplicación web que permite:
- **CU-004:** Consultar horarios de clases para alumnos y profesores
- **CU-PIU:** Visualizar puntos de información en mapa interactivo del campus

## Tecnologías

### Backend
- Java 17 o superior
- Spring Boot 3.5.8
- Maven 3.6+
- PostgreSQL 15.6+

### Frontend
- Vue.js 3.5.13
- Node.js 21.7.1

---

## Requisitos Previos

### 1. Instalar Java 17+
- **Windows/Mac/Linux:** Descargar desde https://adoptium.net/
- Verificar instalación:
  ```bash
  java -version
  ```

### 2. Instalar PostgreSQL 15.6+
- **Windows:** https://www.postgresql.org/download/windows/
- **Mac:** `brew install postgresql@15`
- **Linux:** `sudo apt install postgresql postgresql-contrib`

### 3. Instalar Maven (opcional si usas el wrapper)
- **Windows/Mac/Linux:** https://maven.apache.org/download.cgi
- El proyecto incluye Maven Wrapper (`mvnw` / `mvnw.cmd`)

---

## Configuración de Base de Datos

### 1. Iniciar PostgreSQL

**Windows:**
- Servicios (services.msc) → postgresql-x64-15 → Iniciar

**Mac/Linux:**
```bash
# Mac
brew services start postgresql@15

# Linux
sudo systemctl start postgresql
```

### 2. Crear Base de Datos y Usuario

Abrir terminal y ejecutar:

```bash
psql -U postgres
```

Ejecutar los siguientes comandos SQL:

```sql
-- Crear base de datos
CREATE DATABASE gpiu_db;

-- Crear usuario
CREATE USER gpiu_user WITH PASSWORD 'gpiu_password';

-- Dar permisos
GRANT ALL PRIVILEGES ON DATABASE gpiu_db TO gpiu_user;

-- Conectar a la base de datos
\c gpiu_db

-- Dar permisos en el esquema public
GRANT ALL ON SCHEMA public TO gpiu_user;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO gpiu_user;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO gpiu_user;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON TABLES TO gpiu_user;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON SEQUENCES TO gpiu_user;

-- Salir
\q
```

---

## Instalación y Ejecución

### 1. Clonar el Repositorio

```bash
git clone [URL_DEL_REPOSITORIO]
cd gpiu
```

### 2. Configurar application.properties

El archivo `src/main/resources/application.properties` ya está configurado:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/gpiu_db
spring.datasource.username=gpiu_user
spring.datasource.password=gpiu_password
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

server.port=8081
```

**Nota:** Si necesitas cambiar credenciales de BD, edita este archivo.

### 3. Ejecutar el Backend

**Opción 1 - Usando Maven Wrapper (recomendado):**

```bash
# Windows
mvnw.cmd spring-boot:run

# Mac/Linux
./mvnw spring-boot:run
```

**Opción 2 - Usando Maven instalado:**

```bash
mvn spring-boot:run
```

**Opción 3 - Desde IntelliJ IDEA:**
1. Abrir el proyecto
2. Esperar a que Maven sincronice dependencias
3. Run → Run 'GpiuApplication'

### 4. Verificar que el Backend está corriendo

Deberías ver en la consola:

```
Tomcat started on port 8081 (http) with context path '/'
Started GpiuApplication in X.XXX seconds
```

El backend estará disponible en: `http://localhost:8081`

---

## Endpoints Disponibles

### Horarios

#### Obtener horarios de alumno
```
GET http://localhost:8081/api/horarios/alumno/{id}
```

#### Obtener horarios de profesor
```
GET http://localhost:8081/api/horarios/profesor/{id}
```

#### Obtener horario semanal de alumno
```
GET http://localhost:8081/api/horarios/alumno/{id}/semanal
```

#### Obtener horario semanal de profesor
```
GET http://localhost:8081/api/horarios/profesor/{id}/semanal
```

### Puntos de Información (PIU)

#### Listar todos los PIUs activos
```
GET http://localhost:8081/api/piu
```

#### Obtener PIU más cercano
```
GET http://localhost:8081/api/piu/cercano?lat=-33.4489&lon=-70.6693
```

#### Obtener PIU por ID
```
GET http://localhost:8081/api/piu/{id}
```

---

## Insertar Datos de Prueba

Para probar los endpoints, insertar datos de prueba en PostgreSQL:

```bash
psql -U gpiu_user -d gpiu_db
```

```sql
-- Insertar usuarios
INSERT INTO usuario (nombre, correo) VALUES 
('Diego Altamirano', 'diego.altamirano@usach.cl'),
('María González', 'maria.gonzalez@usach.cl');

-- Insertar alumno
INSERT INTO alumno (id_usuario, carrera, estado_matricula) VALUES 
(1, 'Ingeniería Civil Informática', 'ACTIVO');

-- Insertar profesor
INSERT INTO profesor (id_usuario, departamento, especialidad) VALUES 
(2, 'Ingeniería Informática', 'Ingeniería de Software');

-- Insertar cursos
INSERT INTO curso (codigo, nombre) VALUES 
('13156', 'Fundamentos de Ingeniería de Software'),
('13160', 'Diseño de Bases de Datos');

-- Relacionar alumno con cursos
INSERT INTO alumno_curso (id_usuario, id_curso) VALUES 
(1, 1), (1, 2);

-- Relacionar profesor con cursos
INSERT INTO profesor_curso (id_usuario, id_curso) VALUES 
(2, 1);

-- Insertar salas
INSERT INTO sala (codigo, ubicacion) VALUES 
('INF157', 'Edificio Ingeniería Piso 1'),
('INF526', 'Edificio Ingeniería Piso 5');

-- Insertar horarios
INSERT INTO horario (id_curso, id_sala, dia, hora_inicio, hora_fin) VALUES 
(1, 1, 'JUEVES', '18:45:00', '20:05:00'),
(1, 1, 'JUEVES', '20:05:00', '21:25:00'),
(1, 1, 'SABADO', '08:15:00', '09:35:00'),
(2, 2, 'MIERCOLES', '18:45:00', '20:05:00'),
(2, 1, 'MIERCOLES', '20:05:00', '21:25:00'),
(2, 2, 'SABADO', '09:50:00', '11:10:00');

-- Insertar PIUs
INSERT INTO piu (nombre, descripcion, latitud, longitud, tipo_ubicacion, estado) VALUES 
('PIU Central', 'Punto de información principal', -33.4489, -70.6693, 'EXTERIOR', 'ACTIVO'),
('PIU Ingeniería', 'Punto en Facultad de Ingeniería', -33.4490, -70.6694, 'INTERIOR', 'ACTIVO'),
('PIU Biblioteca', 'Punto en Biblioteca Central', -33.4488, -70.6695, 'INTERIOR', 'ACTIVO');
```

---

## Probar Endpoints

### Usando navegador:

```
http://localhost:8081/api/horarios/alumno/1
http://localhost:8081/api/piu
http://localhost:8081/api/piu/cercano?lat=-33.4489&lon=-70.6693
```

### Usando curl:

```bash
# Horarios de alumno
curl http://localhost:8081/api/horarios/alumno/1

# PIUs activos
curl http://localhost:8081/api/piu

# PIU más cercano
curl "http://localhost:8081/api/piu/cercano?lat=-33.4489&lon=-70.6693"
```

---

## Estructura del Proyecto

```
gpiu/
├── src/
│   ├── main/
│   │   ├── java/com/fingeso/gpiu/
│   │   │   ├── Controller/
│   │   │   │   ├── HorarioController.java
│   │   │   │   └── PiuController.java
│   │   │   ├── Service/
│   │   │   │   ├── HorarioService.java
│   │   │   │   └── PiuService.java
│   │   │   ├── Repository/
│   │   │   │   ├── AlumnoRepository.java
│   │   │   │   ├── CursoRepository.java
│   │   │   │   ├── HorarioRepository.java
│   │   │   │   ├── PiuRepository.java
│   │   │   │   ├── ProfesorRepository.java
│   │   │   │   ├── SalaRepository.java
│   │   │   │   └── UsuarioRepository.java
│   │   │   ├── Entity/
│   │   │   │   ├── AlumnoEntity.java
│   │   │   │   ├── CursoEntity.java
│   │   │   │   ├── HorarioEntity.java
│   │   │   │   ├── PiuEntity.java
│   │   │   │   ├── ProfesorEntity.java
│   │   │   │   ├── SalaEntity.java
│   │   │   │   └── UsuarioEntity.java
│   │   │   └── GpiuApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── pom.xml
└── README.md
```

---

## Arquitectura

### Patrón de Capas

```
Controller (REST API)
    ↓
Service (Lógica de Negocio)
    ↓
Repository (Acceso a Datos)
    ↓
Entity (Modelo de Datos)
    ↓
PostgreSQL (Base de Datos)
```

### Modelo de Datos

**Herencia:**
- Usuario (clase padre)
  - Alumno (hereda de Usuario)
  - Profesor (hereda de Usuario)

**Relaciones:**
- Alumno ↔ Curso (Many-to-Many)
- Profesor ↔ Curso (Many-to-Many)
- Horario → Curso (Many-to-One)
- Horario → Sala (Many-to-One)

---

## Troubleshooting

### Error: "Port 8081 already in use"

**Solución 1:** Cambiar puerto en `application.properties`:
```properties
server.port=8082
```

**Solución 2 (Windows):** Matar proceso en puerto 8081:
```bash
netstat -ano | findstr :8081
taskkill /PID [PID] /F
```

### Error: "Connection refused"

Verificar que PostgreSQL está corriendo:
```bash
# Windows
services.msc → postgresql-x64-15 → debe estar "En ejecución"

# Mac
brew services list | grep postgresql

# Linux
sudo systemctl status postgresql
```

### Error: "Authentication failed for user"

Verificar credenciales en `application.properties` y que el usuario existe en PostgreSQL:
```bash
psql -U postgres -c "\du"
```

### Error: "Database does not exist"

Crear la base de datos:
```bash
psql -U postgres -c "CREATE DATABASE gpiu_db;"
```

### Error: "Permission denied for schema public"

Ejecutar los comandos GRANT del paso "Configuración de Base de Datos".

---

## Tecnologías y Dependencias

### Backend (pom.xml)

```xml
<dependencies>
    <!-- Spring Boot Starter Data JPA -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    
    <!-- Spring Boot Starter Web -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    
    <!-- PostgreSQL Driver -->
    <dependency>
        <groupId>org.postgresql</groupId>
        <artifactId>postgresql</artifactId>
        <scope>runtime</scope>
    </dependency>
    
    <!-- Lombok -->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>
</dependencies>
```

---

## Contacto y Soporte

Para dudas o problemas con el proyecto, contactar al equipo de desarrollo.

---

## Licencia

Proyecto académico - Universidad de Santiago de Chile (USACH)
Curso: Fundamentos de Ingeniería de Software
