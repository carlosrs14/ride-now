CREATE TABLE Locaciones (
    IDLocacion SERIAL PRIMARY KEY,
    Nombre VARCHAR(100) NOT NULL,
    Tipo VARCHAR(20) NOT NULL
);

CREATE TABLE Usuarios (
    IDUsuario SERIAL PRIMARY KEY,
    Nombre VARCHAR(100) NOT NULL,
    Apellido VARCHAR(100) NOT NULL,
    Email VARCHAR(100) NOT NULL UNIQUE,
    FechaNacimiento DATE,
    Telefono VARCHAR(20),
    password VARCHAR(100) NOT NULL
);

CREATE TABLE TiposDePago (
    IDTipoDePago SERIAL PRIMARY KEY,
    Nombre VARCHAR(50) NOT NULL,
    Descripcion VARCHAR(100)
);
-- Descripcion solamente para especificar si es efectivo o pago virtual (PSE)
CREATE TABLE Clientes (
    IDCliente INT PRIMARY KEY,
    FOREIGN KEY (IDCliente) 
        REFERENCES Usuarios(IDUsuario)

);

CREATE TABLE Administradores (
    IDAdministrador INT PRIMARY KEY,
    FOREIGN KEY (IDAdministrador) 
        REFERENCES Usuarios(IDUsuario)
);
-- segun yo los admin deben tener atributos similares a los de cliente

CREATE TABLE PrestadoresDeServicio (
    IDPrestadorDeServicio INT PRIMARY KEY,
    FOREIGN KEY (IDPrestadorDeServicio) 
        REFERENCES Usuarios(IDUsuario)
);

CREATE TABLE Vehiculos (
    IDVehiculo SERIAL PRIMARY KEY,
    Marca VARCHAR(50) NOT NULL,
    Modelo INT NOT NULL,
    Placa VARCHAR(20) NOT NULL UNIQUE,
    TieneAire BOOLEAN,
    Color VARCHAR(20),
    capacidad INT NOT NULL,
    IDPrestadorDeServicio INT NOT NULL,
    FOREIGN KEY (IDPrestadorDeServicio) REFERENCES
        PrestadoresDeServicio(IDPrestadorDeServicio)
);

CREATE TABLE Viajes (
    IDViaje SERIAL PRIMARY KEY,
    Fecha DATE NOT NULL,
    Hora TIME NOT NULL,
    Precio DECIMAL(10, 2) NOT NULL,
    IDVehiculo INT NOT NULL,
    IDLocacionOrigen INT NOT NULL,
    IDLocacionDestino INT NOT NULL,
	FOREIGN KEY (IDLocacionDestino) REFERENCES Locaciones(IDLocacion),
    FOREIGN KEY (IDLocacionOrigen) REFERENCES Locaciones(IDLocacion),
    FOREIGN KEY (IDVehiculo) REFERENCES Vehiculos(IDVehiculo)
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
    IDPago INT NOT NULL,
	IDViaje INT NOT NULL,
	FOREIGN KEY (IDCliente) REFERENCES Clientes(IDCliente),
    FOREIGN KEY (IDPago) REFERENCES Pagos(IDPago),
	FOREIGN KEY (IDViaje) REFERENCES viajes(IDviaje)
);

CREATE TABLE Recargas (
    IDRecarga SERIAL PRIMARY KEY,
    Cantidad DECIMAL(10, 2) NOT NULL,
    IDPrestadorDeServicio INT NOT NULL,
    FOREIGN KEY (IDPrestadorDeServicio) REFERENCES
        PrestadoresDeServicio(IDPrestadorDeServicio)
);

CREATE TABLE Resenas (
    IDResena SERIAL PRIMARY KEY,
    Calificacion INT CHECK (Calificacion BETWEEN 1 AND 5),
    Comentario TEXT,
    FechaResena TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    IDCliente INT NOT NULL,
    IDPrestadorDeServicio INT NOT NULL,
    FOREIGN KEY (IDCliente) REFERENCES Clientes(IDCliente),
    FOREIGN KEY (IDPrestadorDeServicio) REFERENCES
        PrestadoresDeServicio(IDPrestadorDeServicio)
);

CREATE TABLE Denucnias (
    IDResena SERIAL PRIMARY KEY,
    Motivo TEXT NOT NULL,
    FechaDenuncia TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    Estado VARCHAR(20) DEFAULT 'Pendiente',
    IDCliente INT NOT NULL,
    IDPrestadorDeServicio INT NOT NULL,
    FOREIGN KEY (IDCliente) REFERENCES Clientes(IDCliente),
    FOREIGN KEY (IDPrestadorDeServicio) REFERENCES
        PrestadoresDeServicio(IDPrestadorDeServicio)

);
------Insercion de datos

--5 Usuarios
INSERT INTO Usuarios (nombre, apellido, email, fechanacimiento, telefono, password)
VALUES('Juan', 'Perez', 'juan@gmail.com', '1990-10-10', '3000000001', '123456');
INSERT INTO Usuarios (nombre, apellido, email, fechanacimiento, telefono, password)
VALUES('Yayito', 'Perez', 'yayito@gmail.com', '1980-10-10', '3000000001', '123456');
INSERT INTO Usuarios (nombre, apellido, email, fechanacimiento, telefono, password)
VALUES('Travis', 'Bickle', 'travis@gmail.com', '1995-10-10', '3000000001', '123456');
INSERT INTO Usuarios (nombre, apellido, email, fechanacimiento, telefono, password)
VALUES('Cristiano', 'Ronaldo', 'cr7@gmail.com', '2000-10-10', '3000000001', '123456');
INSERT INTO Usuarios (nombre, apellido, email, fechanacimiento, telefono, password)
VALUES('Meikell', 'Montoya', 'meikell@gmail.com', '2005-10-10', '3000000001', '123456');

-- 5 prestadores de servicio
INSERT INTO prestadoresdeservicio (idprestadordeservicio) VALUES (1);
INSERT INTO prestadoresdeservicio (idprestadordeservicio) VALUES (2);
INSERT INTO prestadoresdeservicio (idprestadordeservicio) VALUES (3);
INSERT INTO prestadoresdeservicio (idprestadordeservicio) VALUES (4);
INSERT INTO prestadoresdeservicio (idprestadordeservicio) VALUES (5);

-- 5 vehiculos
INSERT INTO vehiculos (marca, modelo, placa, tieneaire, color, capacidad, idprestadordeservicio)
VALUES ('mazda', 1998, 'AAA123', TRUE, 'rojo', 4, 1);
INSERT INTO vehiculos (marca, modelo, placa, tieneaire, color, capacidad, idprestadordeservicio)
VALUES ('Hyundai', 2000, 'AAA124', FALSE, 'negro', 4, 2);
INSERT INTO vehiculos (marca, modelo, placa, tieneaire, color, capacidad, idprestadordeservicio)
VALUES ('renault', 1990, 'AAA125', TRUE, 'rojo', 4, 3);
INSERT INTO vehiculos (marca, modelo, placa, tieneaire, color, capacidad, idprestadordeservicio)
VALUES ('nissan', 2005, 'AAA126', TRUE, 'blanco', 4, 4);
INSERT INTO vehiculos (marca, modelo, placa, tieneaire, color, capacidad, idprestadordeservicio)
VALUES ('mazda', 2002, 'AAA127', TRUE, 'rojo', 4, 5);

-- 10 locaciones
INSERT INTO locaciones (nombre, tipo) VALUES ('Santa Marta', 'Ciudad'); -- 1
INSERT INTO locaciones (nombre, tipo) VALUES ('Barranquilla', 'Ciudad'); -- 2
INSERT INTO locaciones (nombre, tipo) VALUES ('Cartagena', 'Ciudad'); -- 3
INSERT INTO locaciones (nombre, tipo) VALUES ('Cali', 'Ciudad'); -- 4
INSERT INTO locaciones (nombre, tipo) VALUES ('Medellin', 'Ciudad'); -- 5
INSERT INTO locaciones (nombre, tipo) VALUES ('Las malvinas', 'Barrio'); -- 6
INSERT INTO locaciones (nombre, tipo) VALUES ('Gaira', 'Barrio'); -- 7
INSERT INTO locaciones (nombre, tipo) VALUES ('Libano', 'Barrio'); -- 8

-- 10 viajes
INSERT INTO viajes (fecha, hora, precio, idvehiculo, idlocacionorigen, idlocaciondestino)
VALUES ('2025-5-11', '20:00', 30000, 1, 1, 2);
INSERT INTO viajes (fecha, hora, precio, idvehiculo, idlocacionorigen, idlocaciondestino)
VALUES ('2025-5-12', '12:00', 30000, 2, 1, 3);
INSERT INTO viajes (fecha, hora, precio, idvehiculo, idlocacionorigen, idlocaciondestino)
VALUES ('2025-5-12', '15:30', 30000, 3, 1, 4);
INSERT INTO viajes (fecha, hora, precio, idvehiculo, idlocacionorigen, idlocaciondestino)
VALUES ('2025-5-12', '16:00', 30000, 4, 1, 2);
INSERT INTO viajes (fecha, hora, precio, idvehiculo, idlocacionorigen, idlocaciondestino)
VALUES ('2025-6-12', '17:00', 30000, 5, 2, 1);
INSERT INTO viajes (fecha, hora, precio, idvehiculo, idlocacionorigen, idlocaciondestino)
VALUES ('2025-6-13', '14:00', 30000, 1, 2, 4);
INSERT INTO viajes (fecha, hora, precio, idvehiculo, idlocacionorigen, idlocaciondestino)
VALUES ('2025-6-15', '20:00', 30000, 2, 6, 7);
INSERT INTO viajes (fecha, hora, precio, idvehiculo, idlocacionorigen, idlocaciondestino)
VALUES ('2025-6-16', '18:00', 30000, 3, 7, 6);
INSERT INTO viajes (fecha, hora, precio, idvehiculo, idlocacionorigen, idlocaciondestino)
VALUES ('2025-7-20', '21:00', 30000, 4, 6, 8);
INSERT INTO viajes (fecha, hora, precio, idvehiculo, idlocacionorigen, idlocaciondestino)
VALUES ('2025-7-25', '20:00', 30000, 5, 8, 7);

-- 1 cliente

/*
SELECT viajes.idviaje, viajes.hora, viajes.precio, viajes.fecha, 
                origen.idlocacion AS id_origen, origen.nombre AS nombre_origen, origen.tipo AS tipo_origen, 
                destino.idlocacion AS id_destino, destino.nombre AS nombre_destino, destino.tipo AS tipo_destino 
                FROM viajes 
                INNER JOIN locaciones AS origen ON(viajes.idlocacionorigen = origen.idlocacion) 
                INNER JOIN locaciones AS destino ON (viajes.idlocaciondestino = destino.idlocacion) 
                INNER JOIN prestadoresdeservicio AS pds ON (viajes.idprestadordeservicio = pds.idprestadordeservicio) 
                WHERE origen.nombre LIKE '%San%' AND destino.nombre LIKE '%Ba%' 
                ORDER BY fecha 
                LIMIT 50;
				lower

select * from clientes inner join Usuarios on(Usuarios.IDUsuario = clientes.idcliente)
select * from viajes
*/