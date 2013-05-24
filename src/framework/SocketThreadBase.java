package framework;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public abstract class SocketThreadBase implements Runnable{

	protected BufferedReader reader;
	protected PrintStream printer;
	protected Socket client;
	
	
	public abstract void processRequest() throws Exception;
	public abstract void processResponse();
	public abstract void process() throws Exception;
	
	public SocketThreadBase(Socket client){
		try{
			this.client = client;
			this.reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
			this.printer = new PrintStream(client.getOutputStream());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Override
	public final void run(){
		try{
			processRequest();
			process();
		}catch(Exception e){
			e.printStackTrace();
		}
		processResponse();
	}
	
	public String getRequestString() throws Exception{
		return reader.readLine();
	}
	
	public void respond(String data) throws Exception{
		printer.println(data);
	}
}
