package entry;

import framework.Neo;
import framework.ThreadPool;
import real.Receiver;

public class Entry {
	
	
	public static void main(String[] args) throws Exception{
		setInitParams();
		Receiver.Receive();
	}
	
	public static void setInitParams() throws Exception{
		Receiver.port = 10000;
		Neo.pool =  ThreadPool.initProcessTaskPool();
	}

}
