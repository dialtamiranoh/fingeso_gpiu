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
- Node.js 20+ o 24+
- Axios
- Vue Router
- Leaflet.js (mapas interactivos con OpenStreetMap)

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

### 4. Instalar Node.js 20+
- **Windows/Mac/Linux:** https://nodejs.org/
- Verificar instalación:
  ```bash
  node --version
  npm --version
  ```

### Ejecución rápida con Docker Compose (opcional)

Dentro de la carpeta del repositorio ejecutar:

```bash
docker compose version >/dev/null 2>&1 || sudo apt install -y docker-compose-plugin ; docker compose up -d --build

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

## Ejecutar Frontend

### 1. Instalar dependencias

```bash
cd frontend
npm install
```

### 2. Ejecutar en modo desarrollo

```bash
npm run dev
```

El frontend estará disponible en: `http://localhost:5173`

### 3. Verificar conexión con Backend

Asegúrate de que el backend esté corriendo en `http://localhost:8081` antes de usar el frontend.

---

## Uso de la Aplicación

### Página Principal
- Accede a `http://localhost:5173`
- Verás dos opciones principales: **Consultar Horarios** y **Puntos de Información**

### Consultar Horarios
1. Click en "Ir a Horarios"
2. Selecciona tipo de usuario (Alumno o Profesor)
3. Ingresa ID de usuario (ejemplo: 1 para alumno, 2 para profesor)
4. Click en "Buscar"
5. Verás la tabla de horarios con: Curso, Sala, Día, Hora Inicio, Hora Fin

### Puntos de Información (PIU)
1. Click en "Ver PIUs"
2. Al cargar la página, se muestran automáticamente:
  - **Mapa interactivo** con todos los PIUs del campus USACH usando OpenStreetMap
  - Marcadores en las ubicaciones exactas de cada PIU
3. **Interacción con el mapa:**
  - Zoom: Usa la rueda del mouse o los botones +/-
  - Pan: Arrastra el mapa con el mouse
  - Click en marcadores: Muestra información del PIU (nombre, descripción, ubicación, estado)
4. **Buscar PIU Cercano:** Encuentra el PIU más cercano a las coordenadas del campus central USACH
5. **Lista de PIUs:** Debajo del mapa se muestran tarjetas con todos los PIUs y sus detalles

### Ubicaciones Reales del Campus
El sistema incluye 15 puntos de información en ubicaciones reales:
- Laboratorio CIBAP
- Facultad de Arquitectura
- Programa Bachillerato
- Ingeniería Informática
- Ingeniería Industrial
- Facultad Tecnológica
- Laboratorios de Física
- Ingeniería Eléctrica
- Departamento de Acceso Inclusivo
- Gimnasio USACH
- Escuela de Artes y Oficios
- Departamento de Admisión
- Biblioteca Central
- Salas de Innovación Docente
- Casa Central USACH

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

-- Insertar PIUs con coordenadas reales del campus USACH
INSERT INTO piu (nombre, descripcion, latitud, longitud, tipo_ubicacion, nombre_edificio, estado) VALUES
('PIU Laboratorio CIBAP', 'Punto de información en Laboratorio CIBAP', -33.451938265740665, -70.68692182072735, 'INTERIOR', 'Laboratorio CIBAP', 'ACTIVO'),
('PIU Facultad Arquitectura', 'Punto de información en Facultad de Arquitectura', -33.451542043065224, -70.68573083284173, 'INTERIOR', 'Facultad de Arquitectura', 'ACTIVO'),
('PIU Programa Bachillerato', 'Punto de información en Edificio Programa Bachillerato', -33.451406823804554, -70.68679744539094, 'INTERIOR', 'Edificio Programa Bachillerato', 'ACTIVO'),
('PIU Ingeniería Informática', 'Punto de información en Departamento de Ingeniería Informática', -33.44973919636552, -70.68729701329192, 'INTERIOR', 'Departamento de Ingeniería Informática', 'ACTIVO'),
('PIU Ingeniería Industrial', 'Punto de información en Departamento de Ingeniería Industrial', -33.45026900906702, -70.68837428070603, 'INTERIOR', 'Departamento de Ingeniería Industrial', 'ACTIVO'),
('PIU Facultad Tecnológica', 'Punto de información en Facultad Tecnológica', -33.449670663140104, -70.68845325923971, 'INTERIOR', 'Facultad Tecnológica', 'ACTIVO'),
('PIU Laboratorios Física', 'Punto de información en Laboratorios de Física', -33.45001069283187, -70.6841694629556, 'INTERIOR', 'Laboratorios de Física', 'ACTIVO'),
('PIU Ingeniería Eléctrica', 'Punto de información en Departamento de Ingeniería Eléctrica', -33.45001069283187, -70.6841694629556, 'INTERIOR', 'Departamento de Ingeniería Eléctrica', 'ACTIVO'),
('PIU Acceso Inclusivo', 'Punto de información en Departamento de Acceso Inclusivo', -33.45001069283187, -70.6841694629556, 'INTERIOR', 'Departamento de Acceso Inclusivo', 'ACTIVO'),
('PIU Gimnasio USACH', 'Punto de información en Gimnasio USACH', -33.449459791340935, -70.68569532840895, 'INTERIOR', 'Gimnasio USACH', 'ACTIVO'),
('PIU Escuela Artes y Oficios', 'Punto de información en Escuela de Artes y Oficios', -33.44999902246814, -70.68648122868105, 'INTERIOR', 'Escuela de Artes y Oficios', 'ACTIVO'),
('PIU Departamento Admisión', 'Punto de información en Departamento de Admisión', -33.450211993890456, -70.68536700973577, 'INTERIOR', 'Departamento de Admisión', 'ACTIVO'),
('PIU Biblioteca Central', 'Punto de información en Biblioteca Central', -33.45029533038747, -70.68309418065391, 'INTERIOR', 'Biblioteca Central', 'ACTIVO'),
('PIU Salas Innovación', 'Punto de información en Edificio Salas de Innovación Docente', -33.451021845095546, -70.68433838540292, 'INTERIOR', 'Edificio Salas Innovación Docente', 'ACTIVO'),
('PIU Casa Central', 'Punto de información en Casa Central USACH', -33.448856514534306, -70.6831822179527, 'EXTERIOR', 'Casa Central USACH', 'ACTIVO');
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
├── src/                                    # Backend (Spring Boot)
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
├── frontend/                               # Frontend (Vue.js)
│   ├── src/
│   │   ├── components/
│   │   ├── views/
│   │   │   ├── HomeView.vue
│   │   │   ├── HorariosView.vue
│   │   │   └── PiuView.vue
│   │   ├── router/
│   │   │   └── index.js
│   │   ├── services/
│   │   │   └── api.js
│   │   ├── App.vue
│   │   └── main.js
│   ├── public/
│   ├── package.json
│   └── vite.config.js
├── pom.xml
├── .gitignore
└── README.md
```

---

## Arquitectura

### Stack Tecnológico

**Frontend (Vue.js 3)**
```
Vue Components → Vue Router → Axios → REST API
```

**Backend (Spring Boot)**
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

### Flujo de Datos

```
Usuario → Vue.js (Frontend:5173)
    ↓
Axios HTTP Request
    ↓
Spring Boot (Backend:8081)
    ↓
PostgreSQL (Database:5432)
    ↓
JSON Response
    ↓
Vue.js (Renderizado)
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

### Backend

#### Error: "Port 8081 already in use"

**Solución 1:** Cambiar puerto en `application.properties`:
```properties
server.port=8082
```

**Solución 2 (Windows):** Matar proceso en puerto 8081:
```bash
netstat -ano | findstr :8081
taskkill /PID [PID] /F
```

#### Error: "Connection refused"

Verificar que PostgreSQL está corriendo:
```bash
# Windows
services.msc → postgresql-x64-15 → debe estar "En ejecución"

# Mac
brew services list | grep postgresql

# Linux
sudo systemctl status postgresql
```

#### Error: "Authentication failed for user"

Verificar credenciales en `application.properties` y que el usuario existe en PostgreSQL:
```bash
psql -U postgres -c "\du"
```

#### Error: "Database does not exist"

Crear la base de datos:
```bash
psql -U postgres -c "CREATE DATABASE gpiu_db;"
```

#### Error: "Permission denied for schema public"

Ejecutar los comandos GRANT del paso "Configuración de Base de Datos".

#### Error: "Document nesting depth exceeds maximum"

Si aparece este error al consultar horarios, verifica que todas las entities tengan `@JsonIgnore` en las relaciones inversas:
- `AlumnoEntity.cursos` → `@JsonIgnore`
- `ProfesorEntity.cursos` → `@JsonIgnore`
- `CursoEntity.alumnos` y `CursoEntity.profesores` → `@JsonIgnore`
- `SalaEntity.horarios` → `@JsonIgnore`

### Frontend

#### Error: "Cannot GET /"

El servidor de desarrollo no está corriendo. Ejecutar:
```bash
cd frontend
npm run dev
```

#### Error: "Network Error" o "ERR_CONNECTION_REFUSED"

El backend no está corriendo o está en diferente puerto. Verificar:
1. Backend corriendo en `http://localhost:8081`
2. Archivo `frontend/src/services/api.js` tiene URL correcta

#### Error: "npm: command not found"

Node.js no está instalado. Descargar desde https://nodejs.org/

#### Error: Página en blanco sin errores

1. Abrir consola del navegador (F12)
2. Verificar errores en pestaña Console
3. Verificar que todas las vistas existen en `frontend/src/views/`

#### Error: Página en blanco sin errores

1. Abrir consola del navegador (F12)
2. Verificar errores en pestaña Console
3. Verificar que todas las vistas existen en `frontend/src/views/`

#### Mapa no se muestra

1. Verificar que Leaflet está instalado: `npm list leaflet`
2. Si no está instalado: `cd frontend && npm install leaflet`
3. Limpiar caché y reinstalar: `rm -rf node_modules package-lock.json && npm install`
4. Verificar que el div del mapa tiene altura definida en el CSS

#### Marcadores no aparecen en el mapa

1. Verificar que hay PIUs en la base de datos: `SELECT * FROM piu;`
2. Verificar que el backend está devolviendo datos: `http://localhost:8081/api/piu`
3. Revisar coordenadas: latitud y longitud deben ser números válidos

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

### Frontend (package.json)

```json
{
  "dependencies": {
    "vue": "^3.5.13",
    "vue-router": "^4.4.5",
    "axios": "^1.7.2",
    "leaflet": "^1.9.4"
  }
}
```

**Nota sobre mapas:** El proyecto usa Leaflet.js con OpenStreetMap (gratuito, sin API key necesaria) para visualizar los PIUs en un mapa interactivo del campus USACH.

---

## .gitignore Recomendado

```gitignore
# Maven
target/
pom.xml.tag
pom.xml.releaseBackup
pom.xml.versionsBackup
pom.xml.next
release.properties

# IntelliJ IDEA
.idea/
*.iml
*.iws
*.ipr

# Eclipse
.classpath
.project
.settings/

# VS Code
.vscode/

# Logs
*.log

# OS
.DS_Store
Thumbs.db

# Frontend
frontend/node_modules/
frontend/dist/
frontend/.env
frontend/.env.local
```

---

## Comandos Útiles

### Backend
```bash
# Compilar proyecto
mvn clean install

# Ejecutar tests
mvn test

# Ejecutar aplicación
mvn spring-boot:run
```

### Frontend
```bash
# Instalar dependencias
npm install

# Ejecutar en desarrollo
npm run dev

# Build para producción
npm run build

# Preview build de producción
npm run preview
```

### Base de Datos
```bash
# Conectar a PostgreSQL
psql -U gpiu_user -d gpiu_db

# Ver tablas
\dt

# Ver estructura de tabla
\d nombre_tabla

# Ejecutar script SQL
\i ruta/al/archivo.sql
```

### Agregar Nuevos PIUs

Para agregar nuevos puntos de información con coordenadas reales:

1. **Obtener coordenadas desde Google Maps:**
  - Ve a https://www.google.com/maps
  - Busca la ubicación en el campus USACH
  - Click derecho en el punto exacto
  - Click en las coordenadas que aparecen
  - Se copian automáticamente al portapapeles

2. **Insertar en la base de datos:**
```sql

-- Insertar PIUs con ubicaciones reales del campus USACH
INSERT INTO piu (nombre, descripcion, latitud, longitud, tipo_ubicacion, nombre_edificio, estado) VALUES
('PIU Laboratorio CIBAP', 'Punto de información en Laboratorio CIBAP', -33.451938265740665, -70.68692182072735, 'INTERIOR', 'Laboratorio CIBAP', 'ACTIVO'),
('PIU Facultad Arquitectura', 'Punto de información en Facultad de Arquitectura', -33.451542043065224, -70.68573083284173, 'INTERIOR', 'Facultad de Arquitectura', 'ACTIVO'),
('PIU Programa Bachillerato', 'Punto de información en Edificio Programa Bachillerato', -33.451406823804554, -70.68679744539094, 'INTERIOR', 'Edificio Programa Bachillerato', 'ACTIVO'),
('PIU Ingeniería Informática', 'Punto de información en Departamento de Ingeniería Informática', -33.44973919636552, -70.68729701329192, 'INTERIOR', 'Departamento de Ingeniería Informática', 'ACTIVO'),
('PIU Ingeniería Industrial', 'Punto de información en Departamento de Ingeniería Industrial', -33.45026900906702, -70.68837428070603, 'INTERIOR', 'Departamento de Ingeniería Industrial', 'ACTIVO'),
('PIU Facultad Tecnológica', 'Punto de información en Facultad Tecnológica', -33.449670663140104, -70.68845325923971, 'INTERIOR', 'Facultad Tecnológica', 'ACTIVO'),
('PIU Laboratorios Física', 'Punto de información en Laboratorios de Física', -33.45001069283187, -70.6841694629556, 'INTERIOR', 'Laboratorios de Física', 'ACTIVO'),
('PIU Ingeniería Eléctrica', 'Punto de información en Departamento de Ingeniería Eléctrica', -33.45001069283187, -70.6841694629556, 'INTERIOR', 'Departamento de Ingeniería Eléctrica', 'ACTIVO'),
('PIU Acceso Inclusivo', 'Punto de información en Departamento de Acceso Inclusivo', -33.45001069283187, -70.6841694629556, 'INTERIOR', 'Departamento de Acceso Inclusivo', 'ACTIVO'),
('PIU Gimnasio USACH', 'Punto de información en Gimnasio USACH', -33.449459791340935, -70.68569532840895, 'INTERIOR', 'Gimnasio USACH', 'ACTIVO'),
('PIU Escuela Artes y Oficios', 'Punto de información en Escuela de Artes y Oficios', -33.44999902246814, -70.68648122868105, 'INTERIOR', 'Escuela de Artes y Oficios', 'ACTIVO'),
('PIU Departamento Admisión', 'Punto de información en Departamento de Admisión', -33.450211993890456, -70.68536700973577, 'INTERIOR', 'Departamento de Admisión', 'ACTIVO'),
('PIU Biblioteca Central', 'Punto de información en Biblioteca Central', -33.45029533038747, -70.68309418065391, 'INTERIOR', 'Biblioteca Central', 'ACTIVO'),
('PIU Salas Innovación', 'Punto de información en Edificio Salas de Innovación Docente', -33.451021845095546, -70.68433838540292, 'INTERIOR', 'Edificio Salas Innovación Docente', 'ACTIVO'),
('PIU Casa Central', 'Punto de información en Casa Central USACH', -33.448856514534306, -70.6831822179527, 'EXTERIOR', 'Casa Central USACH', 'ACTIVO');
```

3. **Refrescar la aplicación** - Los nuevos PIUs aparecerán automáticamente en el mapa

---

## Contacto y Soporte

Para dudas o problemas con el proyecto, contactar al equipo de desarrollo.

---

## Licencia

Proyecto académico - Universidad de Santiago de Chile (USACH)
Curso: Fundamentos de Ingeniería de Software
