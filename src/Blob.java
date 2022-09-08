import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.util.*;
public class Blob {
	String translatedName;
	String fileWord;
	String givenName;
	public Blob(String input) throws IOException {
		givenName = input;
		fileWord = getFileString(input);
		translatedName = fileNameCreator(fileWord);
		makeNewFile();
	}
	private static String getFileString(String input) throws IOException {
		Path filePath = Path.of(input);
		return Files.readString(filePath);
	}
	
	//returns the SHA1'd version of the String given in the constructor.
	public String getSha() {
		return translatedName;
	}
	
	//converts an input 
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
	
	//makes new file with a SHA1'd name including the original file's name and its contents,
	//and copies the original contents into the new file.
	public void makeNewFile() throws IOException {
		String newName = translatedName;
		String temp = "";
		String contents = "";
		BufferedReader mike = new BufferedReader(new FileReader(givenName));
		
		while(mike.ready()) {
			temp+=""+(char)mike.read();
		}
		mike.close();
		newName+=fileNameCreator(temp);
		File newFile = new File("C:\\Users\\overe\\eclipse-workspace\\GitBlob\\test\\objects\\"+newName+".txt");
		//newFile.createNewFile();
		FileWriter ike = new FileWriter(newFile);
		ike.write(temp);
		ike.close();
	}
	
}
