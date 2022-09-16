import java.io.FileNotFoundException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class TreeTester {
	public static void main(String[] args) throws NoSuchAlgorithmException, FileNotFoundException
	{
		ArrayList<String> treeContents = new ArrayList<String>();
		treeContents.add("blob : 81e0268c84067377a0a1fdfb5cc996c93f6dcf9f");
		treeContents.add("blob : 01d82591292494afd1602d175e165f94992f6f5f");
		treeContents.add("blob : f1d82236ab908c86ed095023b1d2e6ddf78a6d83");
		treeContents.add("tree : bd1ccec139dead5ee0d8c3a0499b42a7d43ac44b");
		treeContents.add("tree : e7d79898d3342fd15daf6ec36f4cb095b52fd976");
		Tree t = new Tree(treeContents);
	}
}
