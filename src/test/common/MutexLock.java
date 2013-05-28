package test.common;

public class MutexLock {
	public static Object mutex = new Object();
	public static boolean isSignalSent = false;
}
