package real;

import java.net.Socket;
import java.net.URLEncoder;

import common.HttpOperation;

import container.ContainerStatus;

import net.sf.json.JSONObject;

import framework.Neo;
import framework.SocketThreadBase;

public class StatusProcessor extends SocketThreadBase{

	private ContainerStatus containerStatus = null;
	private boolean processStatus = false;
	
	public StatusProcessor(Socket client) throws Exception{
		super(client);
	}

	@Override
	public void processRequest() throws Exception{
		this.containerStatus = new ContainerStatus(this.getRequestString());
		
	}

	@Override
	public void processResponse() {
		try{
			if (processStatus){
				this.respond(CallBackResponse.success.getValue());
			}else{
				this.respond(CallBackResponse.failure.getValue());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void process() throws Exception {
		String responseData = getOutputData();
		if (responseData.isEmpty()){
			this.processStatus = false;
		}else{
			String response = HttpOperation.sendPost(Neo.WebserverAddress, getOutputData());
			this.processStatus = judgeResponse(response);
		}
	}
	
	private String getOutputData() throws Exception{
		return URLEncoder.encode(JSONObject.fromObject(containerStatus).toString(), "UTF-8");
	}
	
	
	private boolean judgeResponse(String callBack){ // judge response from php
		if (CallBackResponse.success.getValue().equalsIgnoreCase(callBack))
			return true;
		else
			return false;
	}
	

}
