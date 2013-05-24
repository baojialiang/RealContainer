package framework;

import java.util.HashMap;
import java.util.concurrent.ThreadPoolExecutor;

import real.SensorCommandConnection;

import container.ContainerRequest;

public class Neo {
	public static ThreadPoolExecutor pool;
	public static SensorCommandConnection sensorCommandConnection;
	public static HashMap<String, ContainerRequest> containerRequest;
	public static String WebserverAddress = "http://";
	
	
	public static void init(){
		Neo.pool =  ThreadPool.initProcessTaskPool(100, 100, 100);
		Neo.sensorCommandConnection = new SensorCommandConnection(20000,30);
	}
	
}
