-- Drop the database if it exists
DROP DATABASE IF EXISTS timefast;

-- Create the database
CREATE DATABASE timefast;
USE timefast;

-- Create tables
CREATE TABLE rol (
    idRol INTEGER AUTO_INCREMENT PRIMARY KEY NOT NULL,
    tipoRol VARCHAR(50) NOT NULL
);

CREATE TABLE tipoUnidad (
    idTipoUnidad INTEGER AUTO_INCREMENT PRIMARY KEY NOT NULL,
    tipoUnidad VARCHAR(50) NOT NULL
);

CREATE TABLE unidad (
    idUnidad INTEGER AUTO_INCREMENT PRIMARY KEY NOT NULL,
    marca VARCHAR(50) NOT NULL,
    modelo VARCHAR(50) NOT NULL,
    anio INT NOT NULL,
    vin VARCHAR(50) NOT NULL UNIQUE,
    noIdentificacion VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE colaborador (
    idColaborador INTEGER AUTO_INCREMENT PRIMARY KEY NOT NULL,
    nombreColaborador VARCHAR(50) NOT NULL,
    apellidoMaterno VARCHAR(50) NOT NULL,
    apellidoPaterno VARCHAR(50) NOT NULL,
    curp VARCHAR(50) NOT NULL UNIQUE,
    correoElectronico VARCHAR(100) NOT NULL UNIQUE,
    noPersonal VARCHAR(50) NOT NULL UNIQUE,
    contrasenia VARCHAR(100) NOT NULL,
    fotografia BLOB,
    idRol INTEGER NOT NULL,
    idUnidad INTEGER NOT NULL,
    FOREIGN KEY (idRol) REFERENCES rol(idRol),
    FOREIGN KEY (idUnidad) REFERENCES unidad(idUnidad)
);

CREATE TABLE cliente (
    idCliente INTEGER AUTO_INCREMENT PRIMARY KEY NOT NULL,
    telefono VARCHAR(15) NOT NULL,
    apellidoMaterno VARCHAR(50) NOT NULL,
    apellidoPaterno VARCHAR(50) NOT NULL,
    correoElectronico VARCHAR(100) NOT NULL UNIQUE,
    contrasenia VARCHAR(100) NOT NULL,
    fotografia BLOB,
    nombreCliente VARCHAR(50) NOT NULL
);

CREATE TABLE envio (
    idEnvio VARCHAR(11) PRIMARY KEY NOT NULL,
    origen VARCHAR(100) NOT NULL,
    calle VARCHAR(100) NOT NULL,
    numeroGuia VARCHAR(10) NOT NULL UNIQUE,
    costoEnvio DECIMAL(10, 2) NOT NULL,
    numeroCasa VARCHAR(10) NOT NULL,
    colonia VARCHAR(100) NOT NULL,
    cp VARCHAR(10) NOT NULL,
    ciudad VARCHAR(50) NOT NULL,
    estado VARCHAR(50) NOT NULL,
    estatus VARCHAR(50) NOT NULL,
    historialEstados VARCHAR(255) NOT NULL, -- Nuevo campo agregado
    idCliente INTEGER NOT NULL,
    FOREIGN KEY (idCliente) REFERENCES cliente(idCliente)
);

CREATE TABLE paquete (
    idPaquete VARCHAR(10) PRIMARY KEY NOT NULL,
    descripcion VARCHAR(255) NOT NULL,
    peso DECIMAL(10, 2) NOT NULL,
    profundidad DECIMAL(10, 2) NOT NULL,
    alto DECIMAL(10, 2) NOT NULL,
    ancho DECIMAL(10, 2) NOT NULL,
    idEnvio VARCHAR(11) NOT NULL,
    FOREIGN KEY (idEnvio) REFERENCES envio(idEnvio)
);

CREATE TABLE asociacionConductorUnidad (
    idColaborador INTEGER NOT NULL,
    idUnidad INTEGER NOT NULL,
    FOREIGN KEY (idColaborador) REFERENCES colaborador(idColaborador),
    FOREIGN KEY (idUnidad) REFERENCES unidad(idUnidad),
    PRIMARY KEY (idColaborador, idUnidad)
);

-- Insert data into tables
INSERT INTO rol (tipoRol) VALUES 
('Administrador'),
('Ejecutivo de tienda'),
('Conductor');

INSERT INTO tipoUnidad (tipoUnidad) VALUES 
('Camión'),
('Automóvil'),
('Motocicleta'),
('SUV'),
('Furgoneta');

INSERT INTO unidad (marca, modelo, anio, vin, noIdentificacion) VALUES 
('Toyota', 'Corolla', 2020, '1HGCM82633A123456', 'TOY-2020-001'),
('Ford', 'F-150', 2021, '1FTFW1EF1BKD45678', 'FORD-2021-002'),
('Honda', 'Civic', 2019, '19XFC2F59KE200789', 'HON-2019-003'),
('Chevrolet', 'Silverado', 2022, '1GCRCREC0EZ123456', 'CHEV-2022-004'),
('Nissan', 'Altima', 2020, '1N4AL3APXFC123456', 'NIS-2020-005');

INSERT INTO colaborador (nombreColaborador, apellidoMaterno, apellidoPaterno, curp, correoElectronico, noPersonal, contrasenia, idRol, idUnidad) VALUES 
('Juan', 'Perez', 'Lopez', 'JUAPPL900101HDFXXX', 'juan.perez@example.com', 'C001', 'password123', 1, 1),
('Maria', 'Gonzalez', 'Hernandez', 'MAGH920202MDFXXX', 'maria.gonzalez@example.com', 'C002', 'password123', 2, 2),
('Carlos', 'Ramírez', 'Diaz', 'CARDF930303HDFXXX', 'carlos.ramirez@example.com', 'C003', 'password123', 3, 3),
('Ana', 'Martinez', 'Cruz', 'ANMC940404MDFXXX', 'ana.martinez@example.com', 'C004', 'password123', 3, 4),
('Luis', 'Hernandez', 'Gomez', 'LUHG950505HDFXXX', 'luis.hernandez@example.com', 'C005', 'password123', 2, 5);

INSERT INTO cliente (telefono, apellidoMaterno, apellidoPaterno, correoElectronico, contrasenia, nombreCliente) VALUES 
('5551234567', 'Perez', 'Lopez', 'cliente1@example.com', 'cliente123', 'Pedro'),
('5557654321', 'Gonzalez', 'Hernandez', 'cliente2@example.com', 'cliente123', 'Marta'),
('5559876543', 'Ramirez', 'Diaz', 'cliente3@example.com', 'cliente123', 'Carlos'),
('5553456789', 'Martinez', 'Cruz', 'cliente4@example.com', 'cliente123', 'Ana'),
('5556789123', 'Hernandez', 'Gomez', 'cliente5@example.com', 'cliente123', 'Luis');

-- Insert data into envio and populate historialEstados based on estatus
INSERT INTO envio (idEnvio, origen, calle, numeroGuia, costoEnvio, numeroCasa, colonia, cp, ciudad, estado, estatus, historialEstados, idCliente) VALUES 
('env0000001', 'Sucursal Centro', 'Primera', 'G000000001', 150.00, '123', 'Centro', '06000', 'Ciudad de Mexico', 'CDMX', 'Detenido', 'En transito,Detenido', 1),
('env0000002', 'Sucursal Norte', 'Segunda', 'G000000002', 200.00, '456', 'Industrial', '07000', 'Ciudad de Mexico', 'CDMX', 'En transito', 'En transito', 2),
('env0000003', 'Sucursal Sur', 'Tercera', 'G000000003', 175.00, '789', 'Las Flores', '09000', 'Ciudad de Mexico', 'CDMX', 'Entregado', 'En transito,Detenido,En transito,Entregado', 3),
('env0000004', 'Sucursal Este', 'Cuarta', 'G000000004', 190.00, '101', 'Jardines', '08000', 'Ciudad de Mexico', 'CDMX', 'En transito', 'En transito', 4),
('env0000005', 'Sucursal Oeste', 'Quinta', 'G000000005', 250.00, '202', 'Colinas', '05000', 'Ciudad de Mexico', 'CDMX', 'Cancelado', 'En transito,Cancelado', 5);

INSERT INTO paquete (idPaquete, descripcion, peso, profundidad, alto, ancho, idEnvio) VALUES 
('pk00000001', 'Electrodoméstico', 5.5, 30.0, 20.0, 15.0, 'env0000001'),
('pk00000002', 'Ropa', 2.0, 25.0, 15.0, 10.0, 'env0000002'),
('pk00000003', 'Libros', 3.5, 20.0, 10.0, 5.0, 'env0000003'),
('pk00000004', 'Herramientas', 8.0, 40.0, 25.0, 20.0, 'env0000004'),
('pk00000005', 'Alimentos', 6.0, 35.0, 22.0, 18.0, 'env0000005');

INSERT INTO asociacionConductorUnidad (idColaborador, idUnidad) VALUES 
(3, 1),
(4, 2),
(3, 3),
(5, 4),
(5, 5);