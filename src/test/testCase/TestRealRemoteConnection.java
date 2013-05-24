package test.testCase;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import real.SensorCommandConnection;
import framework.Neo;

public class TestRealRemoteConnection{

	@Before
	public void setUp() throws Exception {
		Neo.sensorCommandConnection = new SensorCommandConnection(30000, 10);
	}

	@Test
	public void test() {
		while(true){
			try{
				new Thread(Neo.sensorCommandConnection).run();
				
				String callBack = Neo.sensorCommandConnection.sendCommandRequest("true");
				String callBack2 = Neo.sensorCommandConnection.sendCommandRequest("true2");
				String callBack3 = Neo.sensorCommandConnection.sendCommandRequest("true3");
				Assert.assertTrue("true".equalsIgnoreCase(callBack));
				Assert.assertTrue("true2".equalsIgnoreCase(callBack2));
				Assert.assertTrue("true3".equalsIgnoreCase(callBack3));
				return;
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

}
