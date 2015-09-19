import sys
sys.path.append('../..')

import socket
from Servers import data_handler

LOCAL_IP = "127.0.0.1"
UDP_PORT = 5005


def udp():
	count = 0
	print 'Socket UDP inicializado en puerto ' + str(UDP_PORT)
	sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
	sock.bind((LOCAL_IP, UDP_PORT))
	try:
		handler = data_handler.Data_Handler()
		while True:
			data, addr = sock.recvfrom(1024)
			count += 1
			print 'Cliente [' + addr[0] + ': ' + str(addr[1]) + '] : ' + data
			args = data.split("-")
			json_data = {'cliente': args[0], 'latitud': args[1], 'longitud': args[2], 'altitud': args[3],
						 'velocidad': args[4], 'protocolo': 'UDP'}
			handler.insert_location(json_data)
	finally:
		handler.close_connection()
		print "Total de datagramas recibidos: " + str(count)


try:
	udp()
except:
	print "Error: unable to start UDP server"
