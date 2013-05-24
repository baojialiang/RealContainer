package real;

import framework.Neo;
import framework.SocketListener;

public class CommandReceiver extends SocketListener{

	
	public CommandReceiver(int port, int concurrency){
		super(port,concurrency);
	}

	
	@Override
	public void process() throws Exception {
		Neo.pool.execute(new CommandProcessor(getRequest()));
	}
}
