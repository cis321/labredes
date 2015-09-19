Configuracion Base de datos

1. Instalar PostgresSQL
2. Crear base de datos llamada redes con comando:
	sudo -u <usuario_computador> psql -c "CREATE DATABASE redes"
3. Crear tablas de base de datos a partir de archivo 'db.sql' con comando:
	sudo -u <usuario_computador> psql -d redes -a -f <ruta_archivo>

ESTRUCTURA DEL MENSAJE
<ID del cliente> - <latitud> - <longitud> - <altitud> - <velocidad>
El id de cliente debe ser un identificador unico. Se recomienda asignar al thread del cliente un id secuencial o valor generado.