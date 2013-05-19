package entry;

import framework.Neo;
import framework.ThreadPool;
import real.CommandReceiver;

public class Entry {
	
	
	public static void main(String[] args) throws Exception{
		setInitParams();
		Neo.pool.execute(new CommandReceiver(10000));
	}
	
	public static void setInitParams() throws Exception{
		Neo.pool =  ThreadPool.initProcessTaskPool();
	}

}
