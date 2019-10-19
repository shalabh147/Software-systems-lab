//import java.io.InputStream;
import java.io.*;
import java.net.*;
//import java.net.URL;
//import java.net.URLConnection;
import java.util.*;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.HashMap;
/* add some more imports */

public class FetchAndProcessFromNetworkFast implements FetchAndProcess {
    private Map<String, String> data;

    @Override
    public Map<String, String> exposeData() {
	   return data;
    }

    @Override
    public void fetch(List<String> paths) {	

        class Task implements Runnable{
            private Thread t;
            private String urlname;

            Task(String url_name){
                this.urlname=url_name;
            }

            public void run() {
                try{
                    URL url = new URL(urlname);
                    URLConnection yc = url.openConnection();
                    BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
                    String line;
                    while ((line = in.readLine()) != null){
                        if(line.length()!=0){
                            //System.out.println(line);
                            String value = data.containsKey(line) ? data.get(line) : "";
                            value+=url+",";
                            data.put(line,value);
                        }
                    }
                    in.close();
                } catch (Exception e){
                    System.out.println(e);
                }
            }

            public void jointask(){
                try{
                    t.join();
                } catch (Exception e){
                    System.out.println(e);
                }   
        }

            public void start () {
              if (t == null) {
                 t = new Thread (this, "randomThread");
                 t.start();
              }
           }
        }

        data = new HashMap<String,String>();
        Task task = new Task("a_url_that_won't_be_used");
        for(int i=0;i<paths.size();i++){
            task = new Task(paths.get(i));
            task.start();
        }
        task.jointask();
        //System.out.println(data);

	// Implement here, just do it parallely!
    }

    @Override
    public List<String> process() {
	// Implement here
	// Can you make use of the default implementation here?
	// See https://dzone.com/articles/interface-default-methods-java

	return null;
    }
}
