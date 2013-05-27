package test.common;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;


public class CommandSenderDemo implements Runnable{
	public Socket socket;
	public PrintStream printer;
	
	@Override
	public void run() {
		try{
			Thread.sleep(500);
			this.sendRequest();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void sendRequest() throws Exception{
		synchronized(MutexLock.mutex){
			socket = new Socket("localhost", 30000);
			printer =  new PrintStream(socket.getOutputStream());
			printer.println("helloworld"); // to establish connection
			MutexLock.isSignalSent = true;
			MutexLock.mutex.notifyAll(); // release lock
			
		}
		BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		reader.readLine();
		
		printer.println("true");
		reader.readLine();
		
		printer.println("true2");
		reader.readLine();
		
		printer.println("true3");
		reader.readLine();
		
		socket.close();	
	}
}
