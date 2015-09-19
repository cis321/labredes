import socket

UDP_IP = "127.0.0.1"
UDP_PORT = 5005

print "UDP target IP:", UDP_IP
print "UDP target port:", UDP_PORT
sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
count = 0
try:
	for i in range(0,100):
		sock.sendto("perro-554343-5893485-54345-58439859", (UDP_IP, UDP_PORT))
		count += 1
	sock.close()
finally:
	print 'Envio UDP finalizado.' + str(count) + ' datagramas enviados'