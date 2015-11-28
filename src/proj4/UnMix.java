package proj4;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;


public class UnMix {

	public static String fName = "Key";
	String UnMixUsingFile (String filename, String mixedMessage) {
		return mixedMessage;	
	}
	public static void main(String[] args) {
		
		//LinkedList list = new LinkedList();
		try {
			readMixed(fName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void readMixed(String fileName) throws IOException {


		// TODO code application logic here

		// // read KeyWestTemp.txt

		// create token1
		String token1 = "";

		// for-each loop for calculating heat index of May - October

		// create Scanner inFile1
		Scanner inFile1 = new Scanner(new File(fileName + ".txt")).useDelimiter(",\\s*");

		// Original answer used LinkedList, but probably preferable to use ArrayList in most cases
		// List<String> temps = new LinkedList<String>();
		ArrayList<String> temps = new ArrayList<String>();

		// while loop
		while (inFile1.hasNext()) {
			// find next line
			token1 = inFile1.next();
			temps.add(token1);
		}
		inFile1.close();

		String[] tempsArray = temps.toArray(new String[0]);

		for (String s : tempsArray) {
			System.out.println(s);
		}



	}
}