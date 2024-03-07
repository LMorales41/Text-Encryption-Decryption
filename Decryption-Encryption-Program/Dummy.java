

public class Dummy {

	public static void main(String[] args) 
	{
		// TODO: add you logic here
	}


	//this will work only for gradle testing so we can bypass file making/reading and show the test cases directly in TestDummy.java
	public static boolean dummy(String word, String word2) 
	{
		// TOOD: add your logic here
		if (word.equalsIgnoreCase(word2))
		{
			return true;
		}
		return false;
	}

}