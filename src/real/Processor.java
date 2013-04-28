package real;

import java.net.Socket;
import container.ContainerRequest;
import framework.Neo;
import framework.SocketThreadBase;

public class Processor extends SocketThreadBase{
	
	private ContainerRequest containerRequest = null;
	private boolean processStatus = false;
	
	public Processor(Socket client){
		super(client);
	}
	
	
	@Override
	public void processRequest() {
		this.containerRequest = new ContainerRequest(this.getRequestString());
	}

	@Override
	public void processResponse() {
		if (processStatus == true){
			this.respond("Ok");
		}else{
			this.respond("false");
		}
	}
	
	@Override
	public void process() {
		String data = getOutputData();
		if (data.isEmpty()){
			this.processStatus = false;
		}else{
			String responseString = Neo.sendRequest(data);
			this.processStatus = judgeResponse(responseString);
		}
	}
	
	private String getOutputData(){
		if (containerRequest == null){
			return "";
		}else{
			return "";
		}
		
	}
	
	private boolean judgeResponse(String responseString){
		return true;
	}

	
}
 