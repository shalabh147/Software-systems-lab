import java.io.*;
import java.net.*;
import java.util.*;
import java.lang.*;
import java.nio.file.*;

public class BasicClient {

	public static String readFileAsString(String fileName){
    String data = ""; 
    try{ data = new String(Files.readAllBytes(Paths.get(fileName))); }
    catch (Exception e){System.out.println(e); }
    return data; 
  } 

	public static void main(String[] args) throws IOException {

		String[] fullfile= readFileAsString(args[0]).split("\n");
		String fileInput;
		int fileLength = fullfile.length;
		//System.out.println(fileLength);
		//Socket socket;
		//PrintWriter out;
		//BufferedReader in;


		Map<String,Integer> lastKnownValue = new HashMap<String,Integer>();
		int sumOfDeltas=0;
		int fileIndex=0;

		while(true){
			fileInput=fullfile[fileIndex];
			fileIndex+=1;
			String[] command = fileInput.split(" ");

			if(command[0].equals("connect")){
				String hostName=command[1];
				String portNumber=command[2];
				try(Socket socket = new Socket(hostName,Integer.parseInt(portNumber));
					PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
					BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				){
					System.out.println("OK");
					if(fileLength==fileIndex) break;

					while(true){
						fileInput=fullfile[fileIndex];
						fileIndex+=1;
						command = fileInput.split(" ");

						if(command[0].equals("add")){
							out.println(fileInput);
							//System.out.println("sent add");
							String key=command[1];
							int weight = Integer.parseInt(in.readLine());
							lastKnownValue.put(key,weight);
							if(fileLength==fileIndex) break;
						}

						else if(command[0].equals("read")){
							try{

								out.println(fileInput);
								//System.out.println("read sent");
								int weight=Integer.parseInt(in.readLine());
								String key=command[1];
								int lastValue = lastKnownValue.containsKey(key) ? lastKnownValue.get(key) : 0;
								int delta = weight - lastValue;
								sumOfDeltas+=delta;
								//lastKnownValue.put(key,weight);
								String outputLine = weight + " " + String.valueOf(delta);
								System.out.println(outputLine);
								if(fileLength==fileIndex) break;
							} catch (Exception e){
								System.out.println(e);
								if(fileLength==fileIndex) break;
							}
						}

						else if(command[0].equals("sleep")){
							try{
								//System.out.println("sleeping");
								Thread.sleep(Integer.parseInt(command[1]));
							} catch (Exception e){
								System.out.println(e);
							}
						}

						else if(command[0].equals("disconnect")){
							out.println(fileInput);
							try{
								System.out.println("OK");
								break;
							} catch (Exception e){
								System.out.println(e);
							}
						}
					}

					if(fileLength==fileIndex) break;

				} catch (Exception e) {
					System.out.println("No Server");
					System.exit(1);
				}	
			}

			if(fileLength==fileIndex) break;

			else if(command[0].equals("sleep")){
				try{
					Thread.sleep(Integer.parseInt(command[1]));
				} catch (Exception e){
					System.out.println(e);
				}
			}
		}		
		System.out.println(sumOfDeltas);
	}
}