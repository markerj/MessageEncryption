package proj4;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;


public class UnMix {
	LinkedList theMessage;
	String[] informationArray;
	ArrayList<String> temps;
	ArrayList<String> needed;
	ArrayList<String> commands;
	int size;
	String encryptedMsg;
	String theFile;
	char[] msgChar;

	//public static String fName = "Key";
	String UnMixUsingFile (String mixedMessage) {

		return mixedMessage;	
	}
	public static void main(String[] args) {

		//instead of passing commands, pass the undo commands

		UnMix Prog2 = new UnMix();
		Prog2.runUnMixer();

	}

	public void runUnMixer() {
	

		System.out.println("Enter file name to retrieve encrypted message");
		commands = new ArrayList<String>();
		temps = new ArrayList<String>();
		Scanner scan = new Scanner(System.in);
		String s = scan.nextLine();
		theFile = s;


		try {
			readMixed(theFile);
		} catch (IOException e) {

			e.printStackTrace();
		}

		size = temps.size()-1;
		//System.out.println(temps.get(size));
		encryptedMsg = temps.get(size);
		//System.out.println(encryptedMsg.length());
		encryptedMsg = encryptedMsg.substring(1, encryptedMsg.length()-3);
		System.out.println("Encrypted message in " +theFile+ ".txt:" + encryptedMsg);

//		int size2 = temps.size()-1;
//		String each1 = null;
//		for (int i = 0; i < size2; i = i +2 ){
//			if (i > 0){
//				each1 =temps.get(i).substring(1, temps.get(i).length());
//			} 
//			if (i == 0) { 
//				each1 = temps.get(i);
//			}
//			commands.add(each1);
//		}
	//	System.out.println("commands: " + commands);
		int sizeOfMsg = encryptedMsg.length();
		theMessage = new LinkedList();
		//charMsg = new char[];
				 String string = "JavaCodeGeeks";
		       char[] charMsg = encryptedMsg.toCharArray();
		
		for (int i = 0; i < sizeOfMsg; i++ ) {
			theMessage.add(charMsg[i]);
		}
		int size2 = temps.size()-1;
		String each1 = null;
		for (int i = 0; i < size2; i++ ){
			
			if (i > 0){
				each1 = temps.get(i).substring(1, temps.get(i).length());
			} 
			
			if (i == 0) { 
				each1 = temps.get(i).substring(1, temps.get(i).length());
			}
			String each2 = each1.replace('|',',');
		commands.add(each2);
		
		System.out.println("each " + each2);
		}
	
		
		String first = commands.get(0);
		
		//theMessage
		
		System.out.println(theMessage);
		System.out.println("commands: " + commands);
		// for each
		//change first to each
		for (int i = 0; i < commands.size(); i++) {
		if (first.contains("add")) {
			//if the char contains " " postoadd +1?
			String forInt = commands.get(i).substring(4,5);
			int posToAdd = Integer.parseInt(forInt);
			char charToAdd = commands.get(i).charAt(6);
			theMessage.add(posToAdd,charToAdd);
			System.out.println(theMessage);
			//sizeOfMsg++;
		}
		}
		
		System.out.println(theMessage);
		
	}
	public void readMixed(String theFile) throws IOException {
		//if thefile is wrong file name, no such file will be found

		String each = "";
		Scanner inFile1 = new Scanner(new File(theFile + ".txt")).useDelimiter(",");


		while (inFile1.hasNext()) {

			each = inFile1.next();
			
			//String each1 = each.replaceAll("[\\p{Ps}\\p{Pe}]", "");
			
			temps.add(each);
			

		}
	//	System.out.println("temps " + temps);

		inFile1.close();

//		informationArray = temps.toArray(new String[0]);
//		for (String s : informationArray) {
//			System.out.println("array" + s);
//
//		}

	}
}