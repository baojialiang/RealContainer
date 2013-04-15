package common;

import java.util.concurrent.ThreadPoolExecutor;

public class Neo {
	public final static ThreadPoolExecutor pool = ThreadPool.initProcessTaskPool();
}
