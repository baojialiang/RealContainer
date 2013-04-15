package common;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPool {
	
	public static ThreadPoolExecutor initProcessTaskPool(){
	     int poolSize = 10;
	     int maxPoolSize = 15;
	     long keepAliveTime = 10;
	     final ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(
	               100);
	    
	     ThreadPoolExecutor ProcessTaskPool = new ThreadPoolExecutor(poolSize, maxPoolSize,
	             keepAliveTime, TimeUnit.SECONDS, queue);
	     return ProcessTaskPool;
	}
}
