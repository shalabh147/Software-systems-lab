//import java.io.InputStream;
import java.io.*;
import java.net.*;
//import java.net.URL;
//import java.net.URLConnection;
import java.util.*;
import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.HashMap;

public class FetchAndProcessFromNetwork implements FetchAndProcess {
    private Map<String, String> data;

    @Override
    public Map<String, String> exposeData() {
	return data;
    }

    @Override
    public void fetch(List<String> paths) {
	// Implement here
        data = new HashMap<String,String>();

        for(int i=0;i<paths.size();i++){
            try{
                URL url = new URL(paths.get(i));
                URLConnection yc = url.openConnection();
                BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
                String line;
                while ((line = in.readLine()) != null){
                    if(line.length()!=0){
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
        //System.out.println(data);

    }

    @Override
    public List<String> process() {
	// Implement here
	// Can you make use of the default implementation here?
	// See https://dzone.com/articles/interface-default-methods-java
        FetchAndProcess.super.process();

        try{
            String sDriverName = "org.sqlite.JDBC";
            Class.forName(sDriverName);
            Connection conn = DriverManager.getConnection("jdbc:sqlite:pokemon.db");
            //System.out.println("connection made");
            String select = "SELECT pokemon_name FROM pokemon GROUP BY pokemon_name HAVING COUNT(*) > 1 ;";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(select);
            while (rs.next()) {
                System.out.println(rs.getString("pokemon_name"));
            }
        } catch (Exception e){
            System.err.println(e);
        }

	    return null;
    }
}
