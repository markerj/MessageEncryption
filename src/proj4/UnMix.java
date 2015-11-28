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
		encryptedMsg = encryptedMsg.substring(0, encryptedMsg.length()-2);
		System.out.println("Encrypted message in " +theFile+ ".txt: " + encryptedMsg);

		int size2 = temps.size()-1;
		String each1 = null;
		for (int i = 0; i < size2; i++ ){
			if (i > 0){
				each1 =temps.get(i).substring(1, temps.get(i).length());
			} 
			if (i == 0) { 
				each1 = temps.get(i);
			}
			commands.add(each1);
		}
		System.out.println("commands: " + commands);
		
		theMessage = new LinkedList();
		theMessage.add(encryptedMsg);
		System.out.println(theMessage);
		
	}
	public void readMixed(String theFile) throws IOException {
		//if thefile is wrong file name, no such file will be found

		String each = "";
		Scanner inFile1 = new Scanner(new File(theFile + ".txt")).useDelimiter(",");


		while (inFile1.hasNext()) {

			each = inFile1.next();

			String each1 = each.replaceAll("[\\p{Ps}\\p{Pe}]", "");

			temps.add(each1);
		
		}

		inFile1.close();

		//			informationArray = temps.toArray(new String[0]);
		//		for (String s : informationArray) {
		//			System.out.println(s);

		//}

	}
}