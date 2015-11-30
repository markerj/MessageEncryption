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
	ArrayList<Character> clipBoard;
	int sizeOfClipB = 0;


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
		clipBoard = new ArrayList<Character>();
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

			//	System.out.println("each " + each2);
		}
		System.out.println("commands: " + commands);
		// for each
		//change first to each

		//must increase position when char is added to list,
		//represented with inc added to pos ....
		int inc = 0;
		int sizeOfIt = commands.size()-1;

		for (int i = commands.size()-1; i >= 0; i--) {
			if (commands.get(i).contains("addR")) {

				if ( i > 0) {
					String forInt = commands.get(i).substring(5,6);
					int posToAdd = Integer.parseInt(forInt);
					char charToAdd = commands.get(i).charAt(7);
					//inc ++;
					theMessage.add(posToAdd+inc,charToAdd);
				}
				if (i == 0){
					String forInt = commands.get(i).substring(5,6);
					int posToAdd = Integer.parseInt(forInt);
					char charToAdd = commands.get(i).charAt(7);
					theMessage.add(posToAdd,charToAdd);
				}
				sizeOfIt++;
				//System.out.println(theMessage);

			}
			int dec = 0;
			if (commands.get(i).contains("addIb")) {

				//System.out.println("in addib");
				String forInt = commands.get(i).substring(6,7);
				int posToRem = Integer.parseInt(forInt);
				//dec--;
				theMessage.remove(posToRem-1);
				//	theMessage.remove(2);

				//System.out.println(theMessage);
			}
			if (commands.get(i).contains("addS")) {
				int dec1 = 0;
				//System.out.println("in switch");
				if ( i > 0) {
					String forInt = commands.get(i).substring(5,6);
					int posToRem = Integer.parseInt(forInt);
					char charToAdd = commands.get(i).charAt(7);
					dec1++;
					theMessage.remove(posToRem-dec);
					theMessage.add(posToRem-dec,charToAdd);
					//theMessage.add(posToAdd,charToAdd);
				}
				if (i == 0){
					String forInt = commands.get(i).substring(5,6);
					int posToRem = Integer.parseInt(forInt);
					char charToAdd = commands.get(i).charAt(7);
					theMessage.remove(posToRem);
					theMessage.add(posToRem, charToAdd);
				}
				//System.out.println(theMessage);

			}
			if (commands.get(i).contains("copy")) {
				String sizeOfCopy = commands.get(i).substring(5,6);
				int theSize = Integer.parseInt(sizeOfCopy);
				String theChars = commands.get(i).substring(7,7+theSize);

				char[] charArray = theChars.toCharArray();

				for (int y = 0; y < theSize; y++) {
					clipBoard.add(charArray[y]);
					//sizeOfClipB = clipBoard.size();
					sizeOfClipB++;
					setCbSize(sizeOfClipB);
				}

			}
			if (commands.get(i).contains("paste")) {
			int d = 0;
				//have to remove same element theLen times
				String loc = commands.get(i).substring(6,7);
				int locToUnpaste = Integer.parseInt(loc);
				String len = commands.get(i).substring(8,9);
				int theLen = Integer.parseInt(len);
				
				
				//getCbSize;
				//setCbSize(5);

				int g = 0;
				while (g < theLen){

					//System.out.println(g);
					
					g++;
					theMessage.remove(locToUnpaste-1);

				}

			}
			//cut is where i need to use what is on the clipBoard.
			//cut means to add the clipboard to the list

		}

		System.out.println("clipBoard: " + clipBoard);
		System.out.println(theMessage);
		String finalString = theMessage.toString();
		join(finalString);
		System.out.println("The original Message is: " +finalString);

	}
	public void setCbSize(int size) {
		sizeOfClipB = size;
	}
	public int getCbSize() {
		return sizeOfClipB;
	}

	public void readMixed(String theFile) throws IOException {
		//if thefile is wrong file name, no such file will be found

		String each = "";
		Scanner inFile1 = new Scanner(new File(theFile + ".txt")).useDelimiter(",");

		while (inFile1.hasNext()) {

			each = inFile1.next();

			temps.add(each);

		}
		//	System.out.println("temps " + temps);

		inFile1.close();

	}
}