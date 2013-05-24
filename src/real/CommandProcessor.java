package real;

import java.net.Socket;

import net.sf.json.JSONObject;
import container.ContainerRequest;
import framework.Neo;
import framework.SocketThreadBase;

public class CommandProcessor extends SocketThreadBase{
	
	protected ContainerRequest containerRequest;
	protected boolean processStatus = false;
	
	public CommandProcessor(Socket client){
		super(client);
	}
	
	@Override
	public void processRequest() throws Exception{
		this.containerRequest = new ContainerRequest(this.getRequestString());
	}

	@Override
	public void processResponse() {
		try{
			if (processStatus == true)
				this.respond(CallBackResponse.success.getValue());
			else
				this.respond(CallBackResponse.failure.getValue());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Override
	public void process() throws Exception{
		String data = getOutputData();
		String responseString = Neo.sensorCommandConnection.sendCommandRequest(data);
		this.processStatus = judgeResponse(responseString);
	}
	
	//get the json data of command to sensor
	protected String getOutputData(){
		return JSONObject.fromObject(this.containerRequest).toString();
	}
	
	
	//analyze call back from sensor
	protected boolean judgeResponse(String callBack){
		if (CallBackResponse.success.getValue().equalsIgnoreCase(callBack))
			return true;
		else
			return false;
	}
}
 