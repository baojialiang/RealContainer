package real;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class Processor implements Runnable{
	public Socket client;
	
	public Processor(Socket client){
		this.client = client;
	}
	
	@Override
	public void run() {
		try{
			BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
			
			
		}catch(Exception e){
			
		}	
	}
	
}
