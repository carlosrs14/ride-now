CREATE TABLE Locaciones (
    IDLocacion SERIAL PRIMARY KEY,
    Nombre VARCHAR(100) NOT NULL,
    Tipo VARCHAR(20) NOT NULL
);

CREATE TABLE Vehiculos (
    IDVehiculo SERIAL PRIMARY KEY,
    Marca VARCHAR(50) NOT NULL,
    Modelo INT NOT NULL,
    Placa VARCHAR(20) NOT NULL UNIQUE,
    TieneAire BOOLEAN,
    Color VARCHAR(20),
    capacidad INT NOT NULL,
    IDPrestadorDeServicio INT NOT NULL
);

CREATE TABLE Viajes (
    IDViaje SERIAL PRIMARY KEY,
    Fecha DATE NOT NULL,
    Hora INT NOT NULL,
    Precio DECIMAL(10, 2) NOT NULL,
    Tipo VARCHAR(50) NOT NULL,
    IDVehiculo INT NOT NULL,
    IDLocacionOrigen INT NOT NULL,
    IDLocacionDestino INT NOT NULL,
	FOREIGN KEY (IDLocacionDestino) REFERENCES Locaciones(IDLocacion),
    FOREIGN KEY (IDLocacionOrigen) REFERENCES Locaciones(IDLocacion),
    FOREIGN KEY (IDVehiculo) REFERENCES Vehiculos(IDVehiculo)
);
