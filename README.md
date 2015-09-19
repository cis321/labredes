Configuracion Base de datos
===========================

-Instalar PostgresSQL
-Crear base de datos llamada *redes* con comando: <code>sudo -u *usuario_computador* psql -c "CREATE DATABASE redes"</code>
-Crear tablas de base de datos a partir de archivo *db.sql* con comando: <code> sudo -u *usuario_computador* psql -d redes -a -f *ruta_archivo*</code>

ESTRUCTURA DEL MENSAJE
======================

<ID del cliente> - <latitud> - <longitud> - <altitud> - <velocidad>

NOTA: El id de cliente debe ser un identificador unico. Se recomienda asignar al thread del cliente un id secuencial o valor generado.