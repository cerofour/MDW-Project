use corefo_tienda_bd;

USE COREFO_TIENDA_BD;

-- Inserción en la tabla 'tipos_estados'
INSERT INTO tipos_estados (tipo_estado, activo) VALUES 
('Disponible', TRUE),
('No disponible', FALSE);

-- Inserción en la tabla 'tipos_textos'
INSERT INTO tipos_textos (tipo_texto) VALUES 
('Libro'),
('Revista'),
('Diario');

-- Inserción en la tabla 'autores'
INSERT INTO autores (seudonimo, nombre, apellido_paterno, apellido_materno) VALUES 
('J.K.Rowling', 'Joanne', 'Rowling', 'Murray'),
('G.G.Marquez', 'Gabriel', 'García', 'Márquez');

-- Inserción en la tabla 'editoriales'
INSERT INTO editoriales (nombre) VALUES 
('Penguin Random House'),
('Editorial Planeta');

-- Inserción en la tabla 'paises'
INSERT INTO paises (pais) VALUES 
('Perú'),
('Colombia');

-- Inserción en la tabla 'roles_usuarios'
INSERT INTO roles_usuarios (rol_usuario) VALUES 
('Administrador'),
('Cliente');

-- Inserción en la tabla 'categorias'
INSERT INTO categorias (categoria) VALUES 
('Ficción'),
('No ficción');

-- Inserción en la tabla 'niveles_educativos'
INSERT INTO niveles_educativos (nivel_educativo) VALUES 
('Primaria'),
('Secundaria');

-- Inserción en la tabla 'generos'
INSERT INTO generos (genero) VALUES 
('Masculino'),
('Femenino');

-- Inserción en la tabla 'tipos_venta'
INSERT INTO tipos_venta (tipo_venta) VALUES 
('Venta al por menor'),
('Venta al por mayor');

-- Inserción en la tabla 'tipos_documentos'
INSERT INTO tipos_documentos (tipo_documento) VALUES 
('DNI'),
('Pasaporte');

-- Inserción en la tabla 'regiones'
INSERT INTO regiones (pais_id, region) VALUES 
(1, 'Lima'),  -- 1 corresponde a 'Perú'
(2, 'Bogotá');  -- 2 corresponde a 'Colombia'

-- Inserción en la tabla 'provincias'
INSERT INTO provincias (region_id, provincia) VALUES 
(1, 'Lima Metropolitana'),  -- 1 corresponde a 'Lima'
(2, 'Bogotá D.C.');  -- 2 corresponde a 'Bogotá'

-- Inserción en la tabla 'distritos'
INSERT INTO distritos (provincia_id, distrito) VALUES 
(1, 'Miraflores'),  -- 1 corresponde a 'Lima Metropolitana'
(2, 'Chapinero');  -- 2 corresponde a 'Bogotá D.C.'

-- Inserción en la tabla 'direcciones_clientes'
INSERT INTO direcciones_clientes (distrito_id, direccion) VALUES 
(1, 'Av. Larco 123, Miraflores'),  -- 1 corresponde a 'Miraflores'
(2, 'Carrera 7 #85-24, Chapinero');  -- 2 corresponde a 'Chapinero'

-- Inserción en la tabla 'carnets'
INSERT INTO carnets (tipo_estado_id, codigo) VALUES 
(1, 'CRN001'),  -- 1 corresponde a 'Disponible'
(2, 'CRN002');  -- 2 corresponde a 'No disponible'

-- Inserción en la tabla 'clientes'
INSERT INTO clientes (nombre, apellido_paterno, apellido_materno, genero_id, direccion_cliente_id, telefono, correo, documento, tipo_documento_id, carnet_id, nivel_educativo_id) VALUES 
('Juan', 'Perez', 'Lopez', 1, 1, '999999999', 'juan.perez@example.com', '12345678', 1, 1, 1),  -- Corresponde a masculino, dirección 1, DNI, carnet 1, primaria
('Maria', 'Gomez', 'Ramirez', 2, 2, '987654321', 'maria.gomez@example.com', '87654321', 1, 2, 2);  -- Corresponde a femenino, dirección 2, DNI, carnet 2, secundaria

-- Inserción en la tabla 'usuarios'
INSERT INTO usuarios (cliente_id, rol_usuario_id, psk) VALUES 
(1, 1, 'psk_hash_123'),  -- Corresponde a cliente 1, administrador
(2, 2, 'psk_hash_456');  -- Corresponde a cliente 2, cliente

-- Inserción en la tabla 'recursos_textuales'
INSERT INTO recursos_textuales (titulo, fecha_publicacion, stock, numero_paginas, edicion, volumen, codigo, tipo_texto_id, editorial_id) VALUES 
('Cien Años de Soledad', '1967-06-05', 10, 417, 1, 1, 'CA001', 1, 1),  -- Libro, Penguin Random House
('Harry Potter y la Piedra Filosofal', '1997-06-26', 15, 223, 1, 1, 'HP001', 1, 2);  -- Libro, Editorial Planeta

-- Inserción en la tabla 'recursos_textuales_autores'
INSERT INTO recursos_textuales_autores (recurso_textual_id, autor_id) VALUES 
(1, 2),  -- Cien Años de Soledad, Gabriel García Márquez
(2, 1);  -- Harry Potter, J.K. Rowling

-- Inserción en la tabla 'precios'
INSERT INTO precios (recurso_textual_id, precio_compra, precio_venta) VALUES 
(1, 25.00, 50.00),  -- Cien Años de Soledad
(2, 20.00, 40.00);  -- Harry Potter y la Piedra Filosofal

-- Inserción en la tabla 'ventas'
INSERT INTO ventas (cliente_id, recurso_textual_id, tipo_venta_id, precio_venta) VALUES 
(1, 1, 1, 50.00),  -- Cliente 1 compra Cien Años de Soledad al por menor
(2, 2, 1, 40.00);  -- Cliente 2 compra Harry Potter al por menor
