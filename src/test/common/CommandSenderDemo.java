package test.common;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import container.ContainerRequest;


public class CommandSenderDemo implements Runnable{
	public static Socket socket;
	public static PrintStream printer;
	
	//static function to test real connection
	public static void main(String args[]) throws Exception{
		socket = new Socket("localhost", 30000);
		printer =  new PrintStream(socket.getOutputStream());
		printer.println("helloworld"); // to establish connection
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		String req = reader.readLine();
		ContainerRequest containerReq = new ContainerRequest(req);
		
		printer.println(containerReq.getContainerId());
		reader.readLine();
		printer.println(containerReq.getOperation());
		
		socket.close();	
	}
	
	
	//dynamic test from a thread call
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
		String req = reader.readLine();
		ContainerRequest containerReq = new ContainerRequest(req);
		
		printer.println(containerReq.getContainerId());
		reader.readLine();
		printer.println(containerReq.getOperation());
		
		socket.close();	
	}
}
