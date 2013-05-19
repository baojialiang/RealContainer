package test.testCase;

import java.net.Socket;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import real.CommandReceiver;
import framework.Neo;

public class TestRealRemoteConnection{

	private Receiver receiver;
	
	class Receiver extends CommandReceiver{
		public Receiver(int port) throws Exception {
			super(port);
		}
		public Socket getSensorConnection() throws Exception{
			return super.getSensorConnection();
		}
	}
	
	@Before
	public void setUp() throws Exception {
		this.receiver = new Receiver(30000);
	}

	@Test
	public void test() {
		while(true){
			try{
				Neo.setCommandSocket(receiver.getSensorConnection());	
				String callBack = Neo.sendCommandRequest("true");
				String callBack2 = Neo.sendCommandRequest("true2");
				String callBack3 = Neo.sendCommandRequest("true3");
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
