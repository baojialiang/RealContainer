package container;

import net.sf.json.JSONObject;

public class ContainerRequest {

	private String containerId;
	private String operation;
	
	
	public ContainerRequest(){	}
	
	//get data from the json data
	public ContainerRequest(String data){
		JSONObject json = JSONObject.fromObject(data);
		this.containerId = json.getString("containerId");
		this.operation = json.getString("operation");
	}	
	
	//getter and setter
	public String getContainerId() {
		return containerId;
	}
	public void setContainerId(String containerId) {
		this.containerId = containerId;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}	
	
}
