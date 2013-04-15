package entry;

import real.Receiver;

public class Entry {
	
	
	public static void main(String[] args) throws Exception{
		setInitParams();
		Receiver.Receive();
	}
	
	public static void setInitParams(){
		Receiver.port = 10000;
	}

}
