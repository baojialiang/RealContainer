package real;

import java.net.Socket;

import net.sf.json.JSONObject;
import container.ContainerRequest;
import framework.Neo;
import framework.SocketThreadBase;

public class CommandProcessor extends SocketThreadBase{
	
	private ContainerRequest containerRequest = null;
	private boolean processStatus = false;
	
	public CommandProcessor(Socket client){
		super(client);
	}
	
	@Override
	public void processRequest() throws Exception{
		this.containerRequest = new ContainerRequest(this.getRequestString());
	}

	@Override
	public void processResponse() {
		//need to supplement with response
		if (processStatus == true){
			this.respond(CallBackResponse.success.getValue());
		}else{
			this.respond(CallBackResponse.success.getValue());
		}
	}
	
	@Override
	public void process() throws Exception{
		String data = getOutputData();
		if (data.isEmpty()){
			this.processStatus = false;
		}else{
			String responseString = Neo.sendCommandRequest(data);
			this.processStatus = judgeResponse(responseString);
		}
	}
	
	private String getOutputData(){
		return JSONObject.fromObject(this.containerRequest).toString();
	}
	
	private boolean judgeResponse(String callBack){ // judge response from zhiqiang
		if (CallBackResponse.success.getValue().equalsIgnoreCase(callBack))
			return true;
		else
			return false;
	}
	
}
 