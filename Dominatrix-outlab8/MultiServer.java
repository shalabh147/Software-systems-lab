import java.util.*;
import java.net.*;
import java.lang.*;
import java.io.*;
import java.util.concurrent.Executors;

public class MultiServer {

    public static Map<String,Integer> kv = new HashMap< String,Integer>();

    public static void main(String[] args) throws Exception {
        try(ServerSocket serverSocket = new ServerSocket(5000);){
            System.out.println("Listening on 5000");
            var pool = Executors.newFixedThreadPool(20);
            while(true){
                pool.execute(new Task(serverSocket.accept()));
            }
        } catch (Exception e){
            System.out.println(e);
        } 
    }
}


class Task implements Runnable {
    private Socket socket;

    Task(Socket csocket) {
        this.socket = csocket;
    }

    public void run() {
        synchronize(kv){
            try{
                PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                String inputLine, outputLine;

                while ((inputLine = in.readLine()) != null) {
                    if(inputLine.contains("add"))
                    {
                        outputLine = "ADD "+inputLine.substring(4);
                        System.out.println(outputLine);
                        String s=inputLine.substring(4);
                        int x = MultiServer.kv.containsKey(s) ? MultiServer.kv.get(s) : 0;
                        MultiServer.kv.put(s,x+1);
                        out.println(String.valueOf(x+1));
                    }
                    else if(inputLine.contains("read"))
                    {
                        String s=inputLine.substring(5);
                        int x = MultiServer.kv.containsKey(s) ? MultiServer.kv.get(inputLine.substring(5)) : 0;
                        outputLine = "READ "+s+" "+String.valueOf(x);
                        System.out.println(outputLine);
                        out.println(String.valueOf(x));
                    }
                    else if(inputLine.contains("disconnect"))
                    {   outputLine = "DIS";
                        System.out.println(outputLine);
                        break;
                    }
                }
                in.close();
                out.close();
                socket.close();
            } catch(Exception e){
                System.out.println(e);
            }
        }
    }
}
