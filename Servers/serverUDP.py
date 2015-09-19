import socket

LOCAL_IP = "127.0.0.1"
UDP_PORT = 5005
def udp():
	count = 0
	print 'Socket UDP inicializado en puerto ' + str(UDP_PORT)
	sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM) # UDP
	sock.bind((LOCAL_IP, UDP_PORT)) 
	try:
		while True:
			data, addr = sock.recvfrom(1024) # buffer size is 1024 bytes
			if data == 'HELLO':
				sock.sendto("HELLO",addr)
				print 'HELLO'
			elif data == 'GOODBYE':
				sock.sendto("GOODBYE",addr)
				print 'GOODBYE'
			else:
				print 'Cliente [' + addr[0] + ': ' + str(addr[1]) + '] - ' + data
			count+=1
	finally:
		print "\nTotal de peticiones " + str(count)

try:
	udp()
except:
	print "Error: unable to start UDP server"
