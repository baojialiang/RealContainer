package common;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;


public class HttpOperation {
	//Http post code
	public static String sendPost(String url,String param) throws Exception
	{
		 String result="";
		 URL httpurl = new URL(url);
		 HttpURLConnection httpConn = (HttpURLConnection)httpurl.openConnection();       
		 httpConn.setDoOutput(true);
		 httpConn.setDoInput(true);
		 PrintWriter out = new PrintWriter(httpConn.getOutputStream());
		 out.print(param);
		 out.flush();
		 out.close();
		 httpConn.getInputStream();
		 return result;
	}
}
