package real;

import java.net.Socket;

import container.ContainerRequest;
import framework.Neo;
import framework.SocketThreadBase;

public class Processor extends SocketThreadBase{
	
	public Processor(Socket client){
		super(client);
	}
	
	@Override
	public void run() {
		try{
			ContainerRequest containerRequest = getContainerRequest(this.getRequestString());
			this.respond("ok");
			//Neo.socket
			
		}catch(Exception e){
			e.printStackTrace();
		}	
	}
	
	private ContainerRequest getContainerRequest(String data){
		ContainerRequest request = new ContainerRequest();
		return request;
	}
	
	private String getRequestData(ContainerRequest containerRequest){
		return "";
	}

	
}
 