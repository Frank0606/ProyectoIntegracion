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


