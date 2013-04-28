package framework;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public abstract class SocketThreadBase implements Runnable{

	private String requestString = null;
	private BufferedReader reader;
	private PrintStream printer;
	
	public abstract void processRequest();
	public abstract void processResponse();
	public abstract void process();
	
	public SocketThreadBase(Socket client){
		try{
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
	
	public String getRequestString(){
		try{
			if(requestString == null && reader != null){
				return reader.readLine();
			}else{
				return requestString;
			}
		}catch(Exception e){
			e.printStackTrace();
			return "";
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
