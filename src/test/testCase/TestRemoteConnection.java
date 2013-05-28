package test.testCase;

import junit.framework.Assert;
import net.sf.json.JSONObject;

import org.junit.Before;
import org.junit.Test;

import container.ContainerRequest;
import real.SensorCommandConnection;
import test.common.CommandSenderDemo;
import test.common.MutexLock;
import framework.Neo;

public class TestRemoteConnection{
	
	@Before
	public void setUp() throws Exception {
		Neo.sensorCommandConnection = new SensorCommandConnection(30000, 10);
	}

	@Test
	public void test() throws Exception{
		new Thread(Neo.sensorCommandConnection).start();
		Thread.sleep(500);
		
		synchronized(MutexLock.mutex){
			while(!MutexLock.isSignalSent){
				new Thread(new CommandSenderDemo()).start();
				MutexLock.mutex.wait();
				MutexLock.isSignalSent = false;
				break;
			}
			
			ContainerRequest containerReq = getContainerRequest();
			String reqString = JSONObject.fromObject(containerReq).toString();
			
			String idCallBack = Neo.sensorCommandConnection.sendCommandRequest(reqString);
			Assert.assertTrue(containerReq.getContainerId().equalsIgnoreCase(idCallBack));
			
			String operationCallback = Neo.sensorCommandConnection.sendCommandRequest(reqString);
			Assert.assertTrue(containerReq.getOperation().equalsIgnoreCase(operationCallback));
			
			Neo.sensorCommandConnection.closeSensorConnection();
		}
		
	}
	
	protected ContainerRequest getContainerRequest(){
		ContainerRequest containerReq = new ContainerRequest();
		containerReq.setContainerId("10008");
		containerReq.setOperation("open");
		return containerReq;
	}
}
