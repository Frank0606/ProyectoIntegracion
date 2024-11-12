CREATE DATABASE timefast;
USE timefast;


CREATE TABLE rol (
    idRol INTEGER AUTO_INCREMENT PRIMARY KEY NOT NULL,
    tipoRol VARCHAR(50) NOT NULL
);

CREATE TABLE tipoUnidad (
    idTipoUnidad INTEGER AUTO_INCREMENT PRIMARY KEY NOT NULL,
    tipoUnidad VARCHAR(50) NOT NULL
);

CREATE TABLE vehiculo (
    idVehiculo INTEGER AUTO_INCREMENT PRIMARY KEY,
    marca VARCHAR(50) NOT NULL,
    modelo VARCHAR(50) NOT NULL,
    año INT NOT NULL,
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
    idVehiculo INTEGER NOT NULL,
    FOREIGN KEY (idRol) REFERENCES Rol(idRol),
    FOREIGN KEY (idVehiculo) REFERENCES Vehiculo(idVehiculo)
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
    idEnvio INTEGER AUTO_INCREMENT PRIMARY KEY NOT NULL,
    origen VARCHAR(100) NOT NULL,
    calle VARCHAR(100) NOT NULL,
    numeroGuia VARCHAR(50) NOT NULL UNIQUE,
    costoEnvio DECIMAL(10, 2) NOT NULL,
    numeroCasa VARCHAR(10) NOT NULL,
    colonia VARCHAR(100) NOT NULL,
    cp VARCHAR(10) NOT NULL,
    ciudad VARCHAR(50) NOT NULL,
    estado VARCHAR(50) NOT NULL,
    estatus VARCHAR(50) NOT NULL,
    idCliente INTEGER NOT NULL,
    FOREIGN KEY (idCliente) REFERENCES Cliente(idCliente)
);

CREATE TABLE paquete (
    idPaquete INTEGER AUTO_INCREMENT PRIMARY KEY NOT NULL,
    descripcion VARCHAR(255) NOT NULL,
    peso DECIMAL(10, 2) NOT NULL,
    profundidad DECIMAL(10, 2) NOT NULL,
    alto DECIMAL(10, 2) NOT NULL,
    ancho DECIMAL(10, 2) NOT NULL,
    idEnvio INT NOT NULL,
    FOREIGN KEY (idEnvio) REFERENCES Envio(idEnvio)
);

CREATE TABLE asociacionConductorUnidad (
    idColaborador INTEGER NOT NULL,
    idUnidad INTEGER NOT NULL,
    FOREIGN KEY (idColaborador) REFERENCES Colaborador(idColaborador),
    FOREIGN KEY (idUnidad) REFERENCES TipoUnidad(idTipoUnidad),
    PRIMARY KEY (idColaborador, idUnidad)
);


-- Insertar en la tabla rol
INSERT INTO rol (tipoRol) VALUES 
('Administrador'),
('Ejecutivo de tienda'),
('Conductor');

-- Insertar en la tabla tipoUnidad
INSERT INTO tipoUnidad (tipoUnidad) VALUES 
('Camión'),
('Automóvil'),
('Motocicleta'),
('SUV'),
('Furgoneta');

-- Insertar en la tabla vehiculo
INSERT INTO vehiculo (marca, modelo, año, vin, noIdentificacion) VALUES 
('Toyota', 'Corolla', 2020, '1HGCM82633A123456', 'TOY-2020-001'),
('Ford', 'F-150', 2021, '1FTFW1EF1BKD45678', 'FORD-2021-002'),
('Honda', 'Civic', 2019, '19XFC2F59KE200789', 'HON-2019-003'),
('Chevrolet', 'Silverado', 2022, '1GCRCREC0EZ123456', 'CHEV-2022-004'),
('Nissan', 'Altima', 2020, '1N4AL3APXFC123456', 'NIS-2020-005');

-- Insertar en la tabla colaborador
INSERT INTO colaborador (nombreColaborador, apellidoMaterno, apellidoPaterno, curp, correoElectronico, noPersonal, contrasenia, idRol, idVehiculo) VALUES 
('Juan', 'Pérez', 'López', 'JUAPPL900101HDFXXX', 'juan.perez@example.com', 'C001', 'password123', 1, 1),
('María', 'González', 'Hernández', 'MAGH920202MDFXXX', 'maria.gonzalez@example.com', 'C002', 'password123', 2, 2),
('Carlos', 'Ramírez', 'Díaz', 'CARDF930303HDFXXX', 'carlos.ramirez@example.com', 'C003', 'password123', 3, 3),
('Ana', 'Martínez', 'Cruz', 'ANMC940404MDFXXX', 'ana.martinez@example.com', 'C004', 'password123', 3, 4),
('Luis', 'Hernández', 'Gómez', 'LUHG950505HDFXXX', 'luis.hernandez@example.com', 'C005', 'password123', 2, 5);

-- Insertar en la tabla cliente
INSERT INTO cliente (telefono, apellidoMaterno, apellidoPaterno, correoElectronico, contrasenia, nombreCliente) VALUES 
('5551234567', 'Pérez', 'López', 'cliente1@example.com', 'cliente123', 'Pedro'),
('5557654321', 'González', 'Hernández', 'cliente2@example.com', 'cliente123', 'Marta'),
('5559876543', 'Ramírez', 'Díaz', 'cliente3@example.com', 'cliente123', 'Carlos'),
('5553456789', 'Martínez', 'Cruz', 'cliente4@example.com', 'cliente123', 'Ana'),
('5556789123', 'Hernández', 'Gómez', 'cliente5@example.com', 'cliente123', 'Luis');

-- Insertar en la tabla envio
INSERT INTO envio (origen, calle, numeroGuia, costoEnvio, numeroCasa, colonia, cp, ciudad, estado, estatus, idCliente) VALUES 
('Sucursal Centro', 'Primera', 'G001', 150.00, '123', 'Centro', '06000', 'Ciudad de México', 'CDMX', 'Enviado', 1),
('Sucursal Norte', 'Segunda', 'G002', 200.00, '456', 'Industrial', '07000', 'Ciudad de México', 'CDMX', 'En tránsito', 2),
('Sucursal Sur', 'Tercera', 'G003', 175.00, '789', 'Las Flores', '09000', 'Ciudad de México', 'CDMX', 'Entregado', 3),
('Sucursal Este', 'Cuarta', 'G004', 190.00, '101', 'Jardines', '08000', 'Ciudad de México', 'CDMX', 'En tránsito', 4),
('Sucursal Oeste', 'Quinta', 'G005', 250.00, '202', 'Colinas', '05000', 'Ciudad de México', 'CDMX', 'Cancelado', 5);

-- Insertar en la tabla paquete
INSERT INTO paquete (descripcion, peso, profundidad, alto, ancho, idEnvio) VALUES 
('Electrodoméstico', 5.5, 30.0, 20.0, 15.0, 1),
('Ropa', 2.0, 25.0, 15.0, 10.0, 2),
('Libros', 3.5, 20.0, 10.0, 5.0, 3),
('Herramientas', 8.0, 40.0, 25.0, 20.0, 4),
('Alimentos', 6.0, 35.0, 22.0, 18.0, 5);

-- Insertar en la tabla asociacionConductorUnidad
INSERT INTO asociacionConductorUnidad (idColaborador, idUnidad) VALUES 
(3, 1),
(4, 2),
(3, 3),
(5, 4),
(5, 5);


