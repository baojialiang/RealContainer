package framework;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ThreadPoolExecutor;

import container.ContainerRequest;

public class Neo {
	public static ThreadPoolExecutor pool;
	private static Socket commandSocket;
	public static HashMap<String, ContainerRequest> containerRequest;
	public static String WebserverAddress = "http://";
	
	private static BufferedReader reader;
	private static PrintStream printer;

	
	//getter and setter
	public static Socket getCommandSocket() {
		return commandSocket;
	}

	public static void setCommandSocket(Socket commandSocket) {
		Neo.commandSocket = commandSocket;
	}
	
	
	// send command request to Zhiqiang
	public synchronized static String sendCommandRequest(String data) throws Exception{
		if (reader == null || printer == null)
			setReaderAndPrinter();
		printer.println(data);
		printer.flush();
		return reader.readLine();
	}

	private static void setReaderAndPrinter() throws Exception {
		reader = new BufferedReader(new InputStreamReader(
				commandSocket.getInputStream()));
		printer = new PrintStream(commandSocket.getOutputStream());
	}

	public static boolean isCommandConnectionClosed(){
		return commandSocket.isClosed();
	}
	
	
}
