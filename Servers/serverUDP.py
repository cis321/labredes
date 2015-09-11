import socket

LOCAL_IP = "127.0.0.1"
UDP_PORT = 5005
def udp():
	print 'Socket UDP inicializado en puerto ' + str(UDP_PORT)
	sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM) # UDP
	sock.bind((LOCAL_IP, UDP_PORT)) 
	while True:
		data, addr = sock.recvfrom(1024) # buffer size is 1024 bytes
		print 'Cliente [' + addr[0] + ': ' + str(addr[1]) + '] - ' + data

try:
	udp()
except:
	print "Error: unable to start UDP server"