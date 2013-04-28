package framework;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public abstract class SocketThreadBase implements Runnable{

	private String requestString = null;
	private BufferedReader reader;
	private PrintStream printer;
	
	
	public SocketThreadBase(Socket client){
		try{
			this.reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
			this.printer = new PrintStream(client.getOutputStream());
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
	
	
	public boolean respond(String data) throws Exception{
		try{
			printer.println(data);
			printer.close();
			return true;
		}catch(Exception e){
			return false;
		}
	}
}
