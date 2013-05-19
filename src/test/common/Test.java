package test.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.text.MessageFormat;

import junit.framework.Assert;

public class Test {
	public static void main(String args[]) throws IOException{
		Socket socket = new Socket("localhost", 30000);
		PrintStream printer =  new PrintStream(socket.getOutputStream());
		printer.println("helloworld");
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				socket.getInputStream()));
		
		String data = reader.readLine();
		printer.println("true");
		
		String data2 = reader.readLine();
		printer.println("true2");
		
		String data3 = reader.readLine();
		printer.println("true3");

		System.out.println(MessageFormat.format("{0} {1} {2}", data, data2,data3));
		
		Assert.assertTrue("true".equalsIgnoreCase(data));
		Assert.assertTrue("true2".equalsIgnoreCase(data2));
		Assert.assertTrue("true3".equalsIgnoreCase(data3));
		
		socket.close();
	}
}
