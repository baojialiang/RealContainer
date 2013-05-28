package entry;

import framework.Neo;
import real.CommandReceiver;

public class Entry {
	
	
	public static void main(String[] args) throws Exception{
		Neo.init();
		Neo.pool.execute(Neo.sensorCommandConnection);
		Neo.pool.execute(new CommandReceiver(20001,500));
	}
	

}
