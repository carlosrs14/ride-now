CREATE TABLE Usuarios (
    IDUsuario SERIAL PRIMARY KEY,
    Nombre VARCHAR(100) NOT NULL,
    Apellido VARCHAR(100) NOT NULL,
    Email VARCHAR(100) NOT NULL UNIQUE,
    FechaNacimiento DATE,
    Telefono VARCHAR(20),
    password VARCHAR(100) NOT NULL
);

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