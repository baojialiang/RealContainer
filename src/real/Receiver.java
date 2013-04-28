package real;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import framework.Neo;

public class Receiver {
	
	public static int port = 10000;
	private static ServerSocket server;
	
	
	public static void Receive() {
		while(true){
			try{
				Neo.socket = getSensorConnection();	
				processRemoteConnection();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	//from sensor connection from zhiqiang's sensor
	private static Socket getSensorConnection() throws Exception{
		while(true){
			server = new ServerSocket(port);
			Socket socket = server.accept();
			if (validateRemoteAccess(socket)){
				return socket;
			}
		}
	}
	
	//from remote control connection, php side
	private static void processRemoteConnection() throws Exception{
        server = new ServerSocket(port);
        while(true){
            Neo.pool.execute(new Processor(server.accept()));
        }
	}
	
	//validate zhiqiang's sensor connection
	private static boolean validateRemoteAccess(Socket socket) throws Exception{
		BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		return validatePassword(reader.readLine());
	}
	
	//password validation from remote access
	private static boolean validatePassword(String password){
		return "helloworld".equalsIgnoreCase(password);
	}
	

}
