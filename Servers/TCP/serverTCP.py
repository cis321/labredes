import sys
sys.path.append('../..')

import socket
import threading
from Servers import data_handler

LOCAL_IP = "127.0.0.1"
TCP_PORT = 5006
BUFFER_SIZE = 1024


def tcp():
	sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
	sock.bind((LOCAL_IP, TCP_PORT))
	sock.listen(5)
	print 'Socket TCP inicializado en puerto ' + str(TCP_PORT)
	count = 0
	try:
		while True:
			conn, addr = sock.accept()
			thread = threading.Thread(target=protocol_handler, args=(conn, count, addr))
			thread.daemon = True
			thread.start()
			count += 1
	finally:
		sock.close()
		print "Total de conexiones recibidas: " + str(count)

def protocol_handler(conn, identificador, addr):
	try:
		count = 0
		handler = data_handler.Data_Handler()
		for data in readlines(conn):
			print 'Thread ' + str(identificador) + ' recibe[' + addr[0] + ': ' + str(addr[1]) + '] : ' + data
			count += 1
			if (data == 'HELLO'):
				conn.send('HELLO\n')
				print 'Thread ' + str(identificador) + ' responde [' + str(LOCAL_IP) + ': ' + str(TCP_PORT) + '] - ' + 'HELLO'
			elif data == 'GOODBYE':
				break
			else:
				args = data.split("-")
				json_data = {'cliente': args[0], 'latitud': args[1], 'longitud': args[2], 'altitud': args[3],'velocidad': args[4], 'protocolo': 'TCP'}
				handler.insert_location(json_data)
	finally:
		conn.close()
		handler.close_connection()
		print "Total de envios a Thread " + str(identificador) + " : " + str(count)

def readlines(conn, delim='\n'):
	buffer = ''
	data = True
	while data:
		data = conn.recv(BUFFER_SIZE)
		buffer += data
		while buffer.find(delim) != -1:
			line, buffer = buffer.split(delim, 1)
			yield line
	return

try:
	tcp()
except:
	print "Error: unable to start TCP server"
