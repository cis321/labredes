Configuracion Base de datos
===========================

1. Instalar PostgresSQL
2. Crear base de datos llamada *redes* con comando: <code>sudo -u *usuario_computador* psql -c "CREATE DATABASE redes"</code>
3. Crear tablas de base de datos a partir de archivo *db.sql* con comando: <code> sudo -u *usuario_computador* psql -d redes -a -f *ruta_archivo*</code>

Estructura del mensaje
======================

*ID del cliente* - *latitud* - *longitud* - *altitud* - *velocidad*

NOTA: El id de cliente debe ser un identificador unico. Se recomienda asignar al thread del cliente un id secuencial o valor generado.

Protocolos
==========

TCP
---
1. *HELLO* : Indica el inicio de conexion.
2. *Mensaje* : Secuencia de mensajes con la estructura indicada.
3. *GOODBYE* : Cierre de conexion TCP.

UDP
---
1. *Mensaje* : Se envia el mensaje con la estructura indicada.