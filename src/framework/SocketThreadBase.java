package framework;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public abstract class SocketThreadBase implements Runnable{

	private String requestString = null;
	private BufferedReader reader;
	private PrintStream printer;
	protected Socket client;
	public abstract void processRequest() throws Exception;
	public abstract void processResponse() throws Exception;
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
			processResponse();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public String getRequestString() throws Exception{

		if(requestString == null && reader != null){
			return reader.readLine();
		}else{
			return requestString;
		}

	}
	
	public boolean respond(String data){
		try{
			printer.println(data);
			printer.close();
			return true;
		}catch(Exception e){
			return false;
		}
	}
}
