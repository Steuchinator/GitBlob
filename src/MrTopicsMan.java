import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MrTopicsMan {
	private static FileWriter ike;
	private BufferedReader mike;
	public MrTopicsMan() {
		
	}
	
	public static void writeTo(File f, String s) throws IOException {
		ike = new FileWriter(f);
		ike.write(s);
		ike.close();
	}
	
	public String readContents(File f) throws IOException {
		String temp = "";
		mike = new BufferedReader(new FileReader(f));
		while (mike.ready())
			temp += (char)mike.read();
		mike.close();
		return temp;
	}
	
	public String shaify(File f) throws IOException{
		String temp = readContents(f);
		return fileNameCreator(temp);
	}
	
	public String shaify(String s) throws IOException{
		return fileNameCreator(s);
	}
	
	public static String fileNameCreator(String in) {
		try {
	        // getInstance() method is called with algorithm SHA-1
	        MessageDigest md = MessageDigest.getInstance("SHA-1");

	        // digest() method is called
	        // to calculate message digest of the input string
	        // returned as array of byte
	        byte[] messageDigest = md.digest(in.getBytes());

	        // Convert byte array into signum representation
	        BigInteger no = new BigInteger(1, messageDigest);

	        // Convert message digest into hex value
	        String hashtext = no.toString(16);

	        // Add preceding 0s to make it 32 bit
	        while (hashtext.length() < 32) {
	            hashtext = "0" + hashtext;
	        }

	        // return the HashText
	        return hashtext;
	    }

	    // For specifying wrong message digest algorithms
	    catch (NoSuchAlgorithmException e) {
	        throw new RuntimeException(e);
	    }
	}
}
