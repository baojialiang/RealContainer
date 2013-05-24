package real;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import framework.SocketListener;

public class SensorCommandConnection extends SocketListener{
	
	protected Socket commandSocket;
	protected BufferedReader reader;
	protected PrintStream printer;
	protected String connectionPassword = "helloworld";
	
	public SensorCommandConnection(int port, int concurrency){
		super(port,concurrency);
	}
	
	
	@Override
	public void process() throws Exception {
		Socket socket = getRequest();
		if (validateRemoteAccess(socket)){
			this.commandSocket = socket;
			setReaderAndPrinter();
		}
	}
	
	
	// send command request to Sensors
	public synchronized String sendCommandRequest(String data) throws Exception{
		if (isSensorConnectionClosed())
			throw new Exception("Sensor connection is not open right now");
		else{
			printer.println(data);
			printer.flush();
			return reader.readLine();
		}
	}

	
	// set reader and printer
	protected void setReaderAndPrinter() throws Exception {
		reader = new BufferedReader(new InputStreamReader(
				commandSocket.getInputStream()));
		printer = new PrintStream(commandSocket.getOutputStream());
	}

	public boolean isSensorConnectionClosed(){
		if (commandSocket == null)
			return true;
		return commandSocket.isClosed();
	}
	
	//validate zhiqiang's sensor connection
	protected boolean validateRemoteAccess(Socket socket) throws Exception{
		BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		return validatePassword(reader.readLine());
	}
	
	//password validation from remote access
	protected boolean validatePassword(String password){
		return connectionPassword.equalsIgnoreCase(password);
	}	
	
}
