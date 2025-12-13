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
