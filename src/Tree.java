import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
		for(i = 0;i < input.size() ;i++) {
		    listString += input.get(i) + "\n";
		}
//		File folder = new File("objects");
//		folder.mkdir();
		SHA1str = "";
		makeSHA1(listString);
//		System.out.println(SHA1str);
		createFile();
		
	}
	
	public String getTreeName() {
		return SHA1str;
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
		Path p = Paths.get(".\\objects\\" + SHA1str);
        try {
            Files.writeString(p, listString, StandardCharsets.ISO_8859_1);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
	}
	}
}

