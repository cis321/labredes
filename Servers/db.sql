DROP TABLE IF EXISTS Ubicacion;
CREATE TABLE Ubicacion(
	id			SERIAL,
	client_name VARCHAR(50) CONSTRAINT NN_CLIENT_NAME NOT NULL,
	latitud		VARCHAR(50) CONSTRAINT NN_LATITUD NOT NULL,
	longitud	VARCHAR(50) CONSTRAINT NN_LONGITUD NOT NULL,
	altitud		VARCHAR(50) CONSTRAINT NN_ALTITUD NOT NULL,
	velocidad	VARCHAR(50) CONSTRAINT NN_VELOCIDAD NOT NULL,
	fecha		DATE 		CONSTRAINT NN_FECHA NOT NULL,
	CONSTRAINT PK_USUARIOS PRIMARY KEY (id)
);