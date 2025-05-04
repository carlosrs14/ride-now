CREATE TABLE TiposDePago (
    IDTipoDePago SERIAL PRIMARY KEY,
    Nombre VARCHAR(50) NOT NULL,
    Descripcion VARCHAR(100)
);

CREATE TABLE Pagos (
    IDPago SERIAL PRIMARY KEY,
    Cantidad DECIMAL(10, 2) NOT NULL,
    FechaPago DATE NOT NULL,
    IDTipoDePago INT NOT NULL,
    FOREIGN KEY (IDTipoDePago) 
        REFERENCES TiposDePago(IDTipoDePago)
);

CREATE TABLE Reservas (
    IDReserva SERIAL PRIMARY KEY,
    FechaReserva DATE NOT NULL,
    IDCliente INT NOT NULL,
    IDPago INT NULL,
	IDViaje INT NOT NULL,
    FOREIGN KEY (IDPago) 
        REFERENCES Pagos(IDPago)
);
