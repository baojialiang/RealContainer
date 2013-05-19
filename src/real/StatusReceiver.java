package real;

import java.net.ServerSocket;

import framework.Neo;

public class StatusReceiver implements Runnable{

	private int port = 10001;
	private ServerSocket status_server;
	
	
	public StatusReceiver() throws Exception{
		status_server = new ServerSocket(port);
	}
	
	
	@Override
	public void run() {
		while(true){
			try{
				processStatusConnection();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

	
	//receive status from sensors
	private void processStatusConnection() throws Exception{
		while(true){
			Neo.pool.execute(new StatusProcessor(status_server.accept()));
		}
	}
	
}
