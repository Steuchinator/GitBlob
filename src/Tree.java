import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class Tree {
	private String listString;
	private String SHA1str;
	private byte[] SHA1;
	
	public Tree(ArrayList<String> input) throws NoSuchAlgorithmException, FileNotFoundException
	{
		int i;
		listString="";
		for(i = 0;i < input.size() - 1;i++) {
		    listString += input.get(i) + "\n";
		}
		listString += input.get(i);
		makeSHA1(listString);
		createFile();
		
		
	}
	
	public void makeSHA1(String x) throws NoSuchAlgorithmException
	{

	    MessageDigest sha1 = MessageDigest.getInstance("SHA1");
	    SHA1 = sha1.digest((x).getBytes()); 
	    for(byte b : SHA1 ) {
	    	  SHA1str += String.format("%02x",b);
	    	}
	}
	
	public void createFile() throws FileNotFoundException
	{
		File f = new File("objects/SHA1str");
		PrintWriter out = new PrintWriter(f);
		out.print(listString);
		out.close();
		
	}
	
	
	
	
}
