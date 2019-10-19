import java.util.*;
import java.sql.*;
//import java.sql.Connection;
//import java.sql.Statement;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
import java.sql.*;

public interface FetchAndProcess {
    static String DB_NAME = "pokemon.db";
    static String TABLE_NAME = "pokemon";

    /* The map populated by fetch */
    // public Map<String, String> data = new HashMap<String, String>();
    
    // no default implementation
    void fetch(List<String> paths);

    // no default implementation
    Map<String, String> exposeData();
    
    /* Provides a default implementation that does a lot of work:
     * 1. Create the `TABLE_NAME` table if it does not exist (along with a uniqueness constraint).
     * 2. Inserts data into the table, safely. ensuring no duplication.
     * 3. Returns the Connection (useful for the FetchAndProcessNetwork* classes)
     */
    default List<String> process() {
	// you can use exposeData() here.
        Connection conn;
        try{
            String sDriverName = "org.sqlite.JDBC";
            Class.forName(sDriverName);
            conn = DriverManager.getConnection("jdbc:sqlite:pokemon.db");
            // try{
            String make_table = "CREATE TABLE IF NOT EXISTS pokemon (pokemon_name TEXT, source_path TEXT, UNIQUE(pokemon_name,source_path))";
            Statement stmt = conn.createStatement();
            stmt.execute(make_table);
            Map<String,String> mp = exposeData();
            Set<String> s = new HashSet<>();
            s = mp.keySet();

            //String string="";
            //String[] strings=string.split(",");
            //for(int i=0;i<strings.length;i++) System.out.println(strings[i]);

            //iterate through this set 
            for(String key : s){
                String val = mp.get(key);  //val="gym1.gym2," or "" or "gym1,"
                String[] vals=val.split(",");
                for(int i=0;i<vals.length;i++){
                    String x = "INSERT INTO pokemon VALUES('" + key + "','" + vals[i] + "')"; 
                    // String x2="INSERT INTO pokemon VALUES('hey','hello')";
                    //System.out.println(x);
                    stmt = conn.createStatement();
                    try{
                        stmt.execute(x);
                    } catch (Exception e){
                        System.err.println(e.getClass().getName() + ": " + e.getMessage());
                        //System.err.println(e);
                    }
                }
                //String x = "INSERT INTO pokemon VALUES (key,value)"; 
                
            }
        } catch (Exception e){
            System.err.println(e);
        }
	return null;
    }
}