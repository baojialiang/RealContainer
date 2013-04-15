package real;
import java.net.ServerSocket;
import java.net.Socket;
import common.Neo;

public class Receiver {
	
	public static int port = 10000;
	public static Socket socket;

	public static void Receive() throws Exception {
		socket = getSensorConnection();
		getRemoteConnection();
	}
	
	//from sensor connection
	public static Socket getSensorConnection() throws Exception{
		while(true){
			ServerSocket server = new ServerSocket(port);
			return server.accept(); 
		}
	}
	
	//from remote control connection
	public static void getRemoteConnection() throws Exception{
        ServerSocket server = new ServerSocket(port);
        Socket client = null;
        while(true){
        	client = server.accept();
            Neo.pool.execute(new Sender(client));
        }
	}

}
