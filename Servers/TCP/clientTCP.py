import socket
import threading

TCP_IP = "169.254.210.7"
TCP_PORT = 1007
BUFFER_SIZE = 4096

print "TCP target IP:", TCP_IP
print "TCP target port:", TCP_PORT

def send_message(identificador):
	try:
		sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
		sock.connect((TCP_IP, TCP_PORT))
		sock.send('HELLO\n')
		data = sock.recv(BUFFER_SIZE)
		if data == 'HELLO\n':
			for i in range(0, 100):
				sock.send("{identificador}-554343-5893485-54345-58439859\n".format(identificador=identificador))
			sock.send('GOODBYE\n')
	finally:
		sock.close()
		print 'Thread {identificador} envio 100 datagramas TCP'.format(identificador=identificador)

for i in range(0,10):
	thread = threading.Thread(target=send_message, args=(str(i)))
	thread.daemon = True
	thread.start()

print 'Despachados 10 threads'
