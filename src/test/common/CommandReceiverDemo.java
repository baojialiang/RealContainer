package test.common;

import junit.framework.Assert;
import real.CommandReceiver;
import framework.Neo;

public class CommandReceiverDemo extends CommandReceiver{

	public CommandReceiverDemo(int port) throws Exception {
		super(port);
	}

	@Override
	public void run() {
		while(true){
			try{
				String callBack = Neo.sensorCommandConnection.sendCommandRequest("true");
				String callBack2 = Neo.sensorCommandConnection.sendCommandRequest("hehe");
				String callBack3 = Neo.sensorCommandConnection.sendCommandRequest("haha");
				return;
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}
