package common;

import java.net.Socket;
import java.util.concurrent.ThreadPoolExecutor;

public class Neo {
	public final static ThreadPoolExecutor pool = ThreadPool.initProcessTaskPool();
	public static Socket socket;
}
