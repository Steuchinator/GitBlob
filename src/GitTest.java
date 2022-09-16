import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class GitTest {
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception{
		Path p = Paths.get("testFile1.txt");
        try {
            Files.writeString(p, "example", StandardCharsets.ISO_8859_1);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
//		File testFile1 = new File("GitBlob/testFile1.txt");
//		PrintWriter out = new PrintWriter(testFile1);
//		out.print("example");
//		out.close();
//	}
	
	@Test
	void test() throws IOException, NoSuchAlgorithmException {
		Index i = new Index();
		
		Blob b = new Blob("testFile1.txt");
		
		File blobFile = new File ("GitBlob/objects/c3499c2729730a7f807efb8676a92dcb6f8a3f8f");
		assertTrue(blobFile.exists());
	}
	@Test
	void test2() throws IOException {
		Index i = new Index();
		
//		Delete object and index folder, Create and fill a few blank files 
		File index = new File("objects/");
		String[]entries = index.list();
		for(String s: entries){
		    File currentFile = new File(index.getPath(),s);
		    currentFile.delete();
		}
		index.delete();

		Path p2 = Paths.get("testFile2.txt");
        try {
            Files.writeString(p2, "lets test this", StandardCharsets.ISO_8859_1);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Path p3 = Paths.get("testFile3.txt");
        try {
            Files.writeString(p3, "one last time", StandardCharsets.ISO_8859_1);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		
//		Call init
		
//		Verify index and objects folder are created
        Path path = Paths.get("objects");
        assertTrue(Files.exists(path));
		
//		Call add a few times
		
		i.add("testFile1");
		i.add("testFile2");
		i.add("testFIle3");
		
//		Verify index is updated and objects are created
		File tf1 = new File ("objects/","4bcc88817a8cbd2f6475a0388641a12fa6f867b3");
		assertTrue(tf1.exists());
		
		
		File tf2 = new File ("objects/","47256718d66bfd6fab611ca13515f0833cc4f934");
		assertTrue(tf2.exists());
		
		
		String indexTest = getFileString("index.txt");
		
		String indexCorrect = "";
		indexCorrect += ("testFile1 : c3499c2729730a7f807efb8676a92dcb6f8a3f8f");
		indexCorrect += ("\ntestFile2 : 4bcc88817a8cbd2f6475a0388641a12fa6f867b3");
		indexCorrect += ("\ntestFile3 : 47256718d66bfd6fab611ca13515f0833cc4f934");
		
		assertEquals(indexTest,indexCorrect);
		
		
//		Call remove
		i.remove("testFile1");
		i.remove("testFIle2");
		
//		Verify index is updated and objects are removed
		File test1 = new File ("objects/","4bcc88817a8cbd2f6475a0388641a12fa6f867b3");
		assertFalse(test1.exists());
		
		
		File test2 = new File ("objects/","47256718d66bfd6fab611ca13515f0833cc4f934");
		assertFalse(test2.exists());
		
		
		String indexCorrect2 = "";
		indexCorrect2 += ("testFile3 : 47256718d66bfd6fab611ca13515f0833cc4f934");
		
		String indexTest2 = getFileString("index.txt");
		
		assertEquals(indexTest2,indexCorrect2);
		
	}
	
	public String getFileString(String fileName) throws IOException
	{
	FileInputStream fstream = new FileInputStream("index.txt");
	BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

	String strLine = "";
	String str = "";

	//Read File Line By Line
	while ((strLine = br.readLine()) != null)   {
	  // Print the content on the console - do what you want to do
	  str += strLine;
	}
		return str;
	}

}
