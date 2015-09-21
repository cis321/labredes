import psycopg2


class Data_Handler(object):
	global USER
	USER = 'postgres'
	conn = None
	cur = None

	def __init__(self):
		try:
			self.conn = psycopg2.connect("dbname='redes' user='{user}' host='localhost' password=''".format(user=USER))
			self.cur = self.conn.cursor()
		except:
			print "Unable to connect to the database"

	def insert_location(self, location):
		self.cur.execute(
			"INSERT INTO Ubicacion(client_name, latitud, longitud, altitud, velocidad, fecha, protocolo) VALUES(%(cliente)s, %(latitud)s, %(longitud)s, %(altitud)s, %(velocidad)s, now(), %(protocolo)s);",
			location)
		self.conn.commit()

	def close_connection(self):
		self.cur.close()
		self.conn.close()
