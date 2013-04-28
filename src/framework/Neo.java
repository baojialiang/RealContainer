package framework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ThreadPoolExecutor;

import container.ContainerRequest;

public class Neo {
	public static ThreadPoolExecutor pool;
	public static Socket socket;
	public static HashMap<String,ContainerRequest> containerRequest;
	
	private static BufferedReader reader;
	private static PrintStream printer;
	
	public static String sendRequest(String data){
		try{
			if (reader == null || printer == null){
				setReaderAndPrinter();
			}
			printer.println(data);
			return reader.readLine();
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}
	}
	
	private static void setReaderAndPrinter() throws Exception{
		reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		printer = new PrintStream(socket.getOutputStream());
	}
	
}
