import java.util.*;
import java.net.*;
import java.lang.*;
import java.io.*;
public class BasicServer {
	public static void main(String[] args) throws IOException {
	  	Map< String,Integer> hm = new HashMap<String,Integer>();
	    try(
	    	ServerSocket serverSocket = new ServerSocket(5000);	    	
	    ){
	    	System.out.println("Listening on 5000");

	    	while(true){
		    	Socket clientSocket = serverSocket.accept();
		    	PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),true);
		    	BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

		    	String inputLine, outputLine;

		    	while ((inputLine = in.readLine()) != null) {
		    		if(inputLine.contains("add"))
		    		{
		    			outputLine = "ADD "+inputLine.substring(4);
		    			System.out.println(outputLine);
		    			String s=inputLine.substring(4);
		    			int x = hm.containsKey(s) ? hm.get(s) : 0;
		    			hm.put(s,x+1);
		    			out.println(String.valueOf(x+1));
		    		}
		    		else if(inputLine.contains("read"))
		    		{
		    			String s=inputLine.substring(5);
		    			int x = hm.containsKey(s) ? hm.get(inputLine.substring(5)) : 0;
		    			outputLine = "READ "+s+" "+String.valueOf(x);
		    			System.out.println(outputLine);
		    			out.println(String.valueOf(x));
		    		}
		    		else if(inputLine.contains("disconnect"))
		    		{	outputLine = "DIS";
		    			System.out.println(outputLine);
		    			break;
		    		}

		    	}
	    		in.close();
	    		out.close();
	    		clientSocket.close();
	    	}
	    }
	}
}
