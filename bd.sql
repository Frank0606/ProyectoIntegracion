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
    noIdentificacion VARCHAR(50) NOT NULL UNIQUE,
    idTipoUnidad INTEGER,
    FOREIGN KEY (idTipoUnidad) REFERENCES tipoUnidad(idTipoUnidad)
);

CREATE TABLE bajasUnidad (
    idBajasUnidad INTEGER AUTO_INCREMENT PRIMARY KEY NOT NULL,
    vin VARCHAR(50) NOT NULL,
    motivo VARCHAR(255) NOT NULL
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
    licencia VARCHAR(9),
    fotografia BLOB,
    idRol INTEGER NOT NULL,
    idUnidad INTEGER,
    FOREIGN KEY (idRol) REFERENCES rol(idRol),
    FOREIGN KEY (idUnidad) REFERENCES unidad(idUnidad)
);

CREATE TABLE cliente (
    idCliente INTEGER AUTO_INCREMENT PRIMARY KEY NOT NULL,
    telefono VARCHAR(15) NOT NULL,
    apellidoMaterno VARCHAR(50) NOT NULL,
    apellidoPaterno VARCHAR(50) NOT NULL,
    calle VARCHAR(25) NOT NULL,
    colonia VARCHAR(25) NOT NULL,
    cp INT(5) NOT NULL,
    numeroCasa VARCHAR(10) NOT NULL,
    correoElectronico VARCHAR(100) NOT NULL UNIQUE,
    contrasenia VARCHAR(100) NOT NULL,
    fotografia BLOB,
    nombreCliente VARCHAR(50) NOT NULL
);

CREATE TABLE envio (
    idEnvio VARCHAR(11) PRIMARY KEY NOT NULL,
    calle VARCHAR(100) NOT NULL,
    numeroGuia VARCHAR(10) NOT NULL UNIQUE,
    costoEnvio DECIMAL(10, 2) NOT NULL,
    numero VARCHAR(10) NOT NULL,
    colonia VARCHAR(100) NOT NULL,
    cp VARCHAR(10) NOT NULL,
    ciudad VARCHAR(50) NOT NULL,
    estado VARCHAR(50) NOT NULL,
    estatus VARCHAR(50) NOT NULL,
    historialEstados VARCHAR(255) NOT NULL,
    idCliente INTEGER NOT NULL,
    idColaborador INTEGER NOT NULL,
    FOREIGN KEY (idCliente) REFERENCES cliente(idCliente),
    FOREIGN KEY (idColaborador) REFERENCES colaborador(idColaborador)
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

-- Insert data into tables
INSERT INTO rol (tipoRol) VALUES 
('Administrador'),
('Ejecutivo de tienda'),
('Conductor');

INSERT INTO tipoUnidad (tipoUnidad) VALUES 
('Gasolina'),
('Diesel'),
('Eléctrica'),
('Hibrida');

INSERT INTO unidad (marca, modelo, anio, vin, noIdentificacion, idTipoUnidad) VALUES 
('Toyota', 'Corolla', 2020, '1HGCM82633A123456', '1HGC2020', 2),
('Ford', 'F-150', 2021, '1FTFW1EF1BKD45678', '1FTF2021', 4),
('Honda', 'Civic', 2019, '19XFC2F59KE200789', '19XF2019', 3),
('Chevrolet', 'Silverado', 2022, '1GCRCREC0EZ123456', '1GCR2022', 1),
('Nissan', 'Altima', 2020, '1N4AL3APXFC123456', '1N4A2020', 2);

INSERT INTO colaborador (nombreColaborador, apellidoMaterno, apellidoPaterno, curp, correoElectronico, noPersonal, contrasenia, idRol, idUnidad, licencia) VALUES 
('Juan', 'Perez', 'Lopez', 'JUAPPL900101HDFXXX', 'juan.perez@example.com', 'C001', 'password123', 1, NULL, NULL), 
('Maria', 'Gonzalez', 'Hernandez', 'MAGH920202MDFXXX', 'maria.gonzalez@example.com', 'C002', 'password123', 2, NULL, NULL), 
('Carlos', 'Ramírez', 'Diaz', 'CARDF930303HDFXXX', 'carlos.ramirez@example.com', 'C003', 'password123', 3, 3, 'USWTR109P'), 
('Ana', 'Martinez', 'Cruz', 'ANMC940404MDFXXX', 'ana.martinez@example.com', 'C004', 'password123', 3, 4, 'USW98I4RT'), 
('Luis', 'Hernandez', 'Gomez', 'LUHG950505HDFXXX', 'luis.hernandez@example.com', 'C005', 'password123', 2, NULL, NULL);

INSERT INTO cliente (telefono, apellidoMaterno, apellidoPaterno, correoElectronico, contrasenia, nombreCliente, calle, colonia, cp, numeroCasa) VALUES 
('5551234567', 'Perez', 'Lopez', 'cliente1@example.com', 'cliente123', 'Pedro', 'Calle 1', 'Colonia 1', '91000', '12'),
('5557654321', 'Gonzalez', 'Hernandez', 'cliente2@example.com', 'cliente123', 'Marta', 'Calle 2', 'Colonia 2', '92000', '34A'),
('5559876543', 'Ramirez', 'Diaz', 'cliente3@example.com', 'cliente123', 'Carlos', 'Calle 3', 'Colonia 3', '93000', '56'),
('5553456789', 'Martinez', 'Cruz', 'cliente4@example.com', 'cliente123', 'Ana', 'Calle 4', 'Colonia 4', '94000', '78B'),
('5556789123', 'Hernandez', 'Gomez', 'cliente5@example.com', 'cliente123', 'Luis', 'Calle 5', 'Colonia 5', '95000', '90');

-- Insert data into envio and populate historialEstados based on estatus
INSERT INTO envio (idEnvio, calle, numeroGuia, costoEnvio, numero, colonia, cp, ciudad, estado, estatus, historialEstados, idCliente, idColaborador) VALUES 
('env0000001', 'Primera', 'G000000001', 150.00, '123', 'Centro', '06000', 'Ciudad de Mexico', 'CDMX', 'DETENIDO', 'PENDIENTE_05/01/24-12:00_C001:EN TRANSITO_07/01/24-12:25_C003:DETENIDO_06/01/24-13:36_C003:', 1, 3),
('env0000002', 'Segunda', 'G000000002', 200.00, '456', 'Industrial', '07000', 'Ciudad de Mexico', 'CDMX', 'EN TRANSITO', 'PENDIENTE_05/01/24-12:00_C001:EN TRANSITO_06/01/24-13:36_C003:', 2, 3),
('env0000003', 'Tercera', 'G000000003', 175.00, '789', 'Las Flores', '09000', 'Ciudad de Mexico', 'CDMX', 'ENTREGADO', 'PENDIENTE_05/01/24-12:00_C001:EN TRANSITO_06/01/24-17:36_C004:DETENIDO_06/01/24-18:20_C001:EN TRANSITO_06/01/24-21:15_C004:ENTREGADO_07/01/24-08:59_C004:', 3, 4),
('env0000004', 'Cuarta', 'G000000004', 190.00, '101', 'Jardines', '08000', 'Ciudad de Mexico', 'CDMX', 'EN TRANSITO', 'PENDIENTE_05/01/24-12:00_C001:EN TRANSITO_07/01/24-18:40_C004:', 4, 4),
('env0000005', 'Quinta', 'G000000005', 250.00, '202', 'Colinas', '05000', 'Ciudad de Mexico', 'CDMX', 'CANCELADO', 'PENDIENTE_05/01/24-12:00_C001:EN TRANSITO_07/01/24-10:36_C003:CANCELADO_07/01/24-11:02_C001:', 5, 3);

INSERT INTO paquete (idPaquete, descripcion, peso, profundidad, alto, ancho, idEnvio) VALUES 
('P001', 'Paquete 1', 2.5, 20.0, 10.0, 5.0, 'env0000001'),
('P002', 'Paquete 2', 3.0, 25.0, 15.0, 7.0, 'env0000002'),
('P003', 'Paquete 3', 1.5, 10.0, 5.0, 3.0, 'env0000003'),
('P004', 'Paquete 4', 4.0, 30.0, 20.0, 10.0, 'env0000004'),
('P005', 'Paquete 5', 2.0, 15.0, 8.0, 4.0, 'env0000005');
