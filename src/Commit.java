import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Commit {
	private Commit child;
	private Commit parent;
	private String summary = "";
	private String author = "";
	private String date = "";
//	private String pTree = "";
	private String fileName = "";
	private String contents = "";
	private File f;
	private Tree pTree = new Tree(new ArrayList<String>());
	
	public Commit(String treeValue, String summaryInput, String authorInput, Commit parentPointer) throws NoSuchAlgorithmException, IOException {
		
		summary = summaryInput;
		author = authorInput;
		String temp = "";
		if(parentPointer!=null) {
			setParent(parentPointer);
			parentPointer.setChild(this);
			temp = parentPointer.getContents();
		}
		else {
			setParent(null);
		}
		
		if(treeValue == null) {
			pTree = null;
		}
		
		contents = summary+getDate()+author+temp;
		fileName = shaCreator(contents);
		
		f = new File(".\\objects\\"+fileName);
		writeFile();
	}
	
	public void setParent(Commit input) {
		parent = input;
	}
	
	public void setChild(Commit input) {
		child = input;
	}
	
	public String getDate() {
		String temp = "";
		DateTimeFormatter datey = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		temp = datey.format(now);
		return temp;
	}
	
	public String getContents() {
		return ".\\objects\\"+fileName;
	}
	
	public void writeFile() throws IOException {
		String pT = "";
		String pa = "";
		String ch = "";
		if(pTree!=null)
			pT = ".\\objects\\"+pTree.getTreeName();
		if(parent!=null)
			pa = parent.getContents();
		if(child!=null)
			pa = parent.getContents();
		
		MrTopicsMan.writeTo(f, pT+"\n"+pa+"\n"+ch+"\n"+author+"\n"+getDate()+"\n"+summary);
		
	}
	
	public static String shaCreator(String in) {
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