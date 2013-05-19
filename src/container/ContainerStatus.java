package container;

import net.sf.json.JSONObject;

public class ContainerStatus {
	private String containerId;
	private String temperature;
	
	
	public ContainerStatus(){}
	
	public ContainerStatus(String data) throws Exception{
		JSONObject json = JSONObject.fromObject(data);
		this.containerId = json.getString("containerId");
		this.temperature = json.getString("temperature");
	}
	

	//getter and setter
	public String getContainerId() {
		return containerId;
	}


	public void setContainerId(String containerId) {
		this.containerId = containerId;
	}


	public String getTemperature() {
		return temperature;
	}


	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	
}
