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
				Neo.setCommandSocket(getSensorConnection());	
				String callBack = Neo.sendCommandRequest("true");
				String callBack2 = Neo.sendCommandRequest("hehe");
				String callBack3 = Neo.sendCommandRequest("haha");
				return;
			}catch(Exception e){
				e.printStackTrace();
				this.run();
			}
		}
	}
}
