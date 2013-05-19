package real;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import framework.Neo;

public class CommandReceiver implements Runnable{

	private ServerSocket request_server;
	
	public CommandReceiver(int port) throws Exception{
		request_server = new ServerSocket(port,30);
	}
	
	@Override
	public void run() {
		while(true){
			try{
				Neo.setCommandSocket(getSensorConnection());	
				processRemoteConnection();
			}catch(Exception e){
				e.printStackTrace();
				if (Neo.isCommandConnectionClosed())
					this.run();
				}
			}
	}
	
	//from sensor connection from zhiqiang's sensor
	protected Socket getSensorConnection() throws Exception{
		while(true){
			Socket socket = request_server.accept();
			if (validateRemoteAccess(socket)){
				return socket;
			}
		}
	}
	
	
	//from remote control connection, php side
	protected void processRemoteConnection() throws Exception{
        while(true){
            Neo.pool.execute(new CommandProcessor(request_server.accept()));
        }
	}
	
	//validate zhiqiang's sensor connection
	protected boolean validateRemoteAccess(Socket socket) throws Exception{
		BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		return validatePassword(reader.readLine());
	}
	
	//password validation from remote access
	protected boolean validatePassword(String password){
		return "helloworld".equalsIgnoreCase(password);
	}


}
