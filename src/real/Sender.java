package real;

import java.io.PrintStream;
import java.net.Socket;

public class Sender implements Runnable{
	public Socket client;
	
	public Sender(Socket client){
		this.client = client;
	}
	
	@Override
	public void run(){
		try {
			Thread.sleep(5000);
            System.out.println("local port: " + client.getLocalPort());
			System.out.println("remote port: " + client.getPort());
			PrintStream out =  new PrintStream(client.getOutputStream());
            out.println("hello world");
            out.flush();
            out.close();
            client.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
