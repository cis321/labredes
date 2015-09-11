import socket

LOCAL_IP = "127.0.0.1"
TCP_PORT = 5006
def tcp():
	print tcp
	BUFFER_SIZE = 1024
	sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
	sock.bind((LOCAL_IP, TCP_PORT))
	sock.listen(5) 
	while True:
		conn, addr = sock.accept()
		data = conn.recv(BUFFER_SIZE)
		print 'Connection address:', addr
		print "received data:", data
	conn.close()

try:
	tcp()
except:
	print "Error: unable to start TCP server"