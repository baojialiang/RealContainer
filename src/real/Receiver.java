package real;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import common.Neo;

public class Receiver {
	
	public static int port = 10000;

	public static void Receive() {
		while(true){
			try{
				getSensorConnection();	
				getRemoteConnection();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	//from sensor connection	from zhiqiang's sensor
	public static void getSensorConnection() throws Exception{
		while(true){
			ServerSocket server = new ServerSocket(port);
			Neo.socket = server.accept();
			if (validateRemoteAccess(Neo.socket)){
				return;
			}
		}
	}
	
	//from remote control connection, php side
	public static void getRemoteConnection() throws Exception{
        ServerSocket server = new ServerSocket(port);
        while(true){
        	Socket client = server.accept();
            Neo.pool.execute(new Processor(client));
        }
	}
	
	//validate zhiqiang's sensor connection
	public static boolean validateRemoteAccess(Socket socket) throws Exception{
		BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		if ("helloworld".equalsIgnoreCase(reader.readLine()))
			return true;
		else
			return false;
	}

}
