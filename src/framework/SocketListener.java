package framework;

import java.net.ServerSocket;
import java.net.Socket;

public abstract class SocketListener implements Runnable{

	protected ServerSocket server;
	protected int port;
	protected int concurrency; 
	
	public SocketListener(int port,int concurrency){
		this.port = port;
		this.concurrency = concurrency;
	}
	
	protected void closeLocalPortBinding(){
		try{
			if(server != null)
				server.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	protected void resetConnection() throws Exception{
		this.server = new ServerSocket(this.port,this.concurrency);
	}
	
	
	protected Socket getRequest() throws Exception{
		return this.server.accept();
	}
	
	@Override
	public void run(){
		while(true){
			try{
				closeLocalPortBinding();
				resetConnection();
				while(true){
					try{
						process();
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public abstract void process() throws Exception;
	
	
	
}
