/*****************************************************************
Decrypts the encrypted message taken from a file that mix class 
wrote to. File contains the information to undo the mixed message
and the mixed message itself.

@author John Marker
@version Fall 2015
 *****************************************************************/
package proj4;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class UnMix {

	/** linkedlist holding the mixed and eventually unmixed message */
	LinkedList theMessage;

	/** String array holding chars */
	String[] informationArray;

	/** ArrayList holding commands read from file */
	ArrayList<String> temps;

	/** ArrayList holding more commands read from file */
	ArrayList<String> needed;

	/** ArrayList holding final commands useful for this class */
	ArrayList<String> commands;

	/** holds size of theMessage */
	int size;

	/** holds the encrypted msg after being read from file
	 * and seperated from undo commands */
	String encryptedMsg;

	/** holds the file name given by the user from scanner */
	String theFile;

	/** char array holding each char of msg */
	char[] msgChar;

	/** ArrayList holding the characters copied by cut or copy */
	ArrayList<Character> clipBoard;

	/** holds the size of clipboard */
	int sizeOfClipB = 0;

	/** holds the final message */
	String finalMessage = null;

	/*****************************************************************
	encapsulation needs to be fixed
	 *****************************************************************/

	String UnMixUsingFile (String unMixedMessage) {
		String finalUnmixedMsg= unMixedMessage;
		return finalUnmixedMsg;	
	}

	/*****************************************************************
	main creates a new UnMix and runs the UnMixer method
	 *****************************************************************/
	public static void main(String[] args) {

		UnMix Prog2 = new UnMix();
		Prog2.runUnMixer();

	}


	/*****************************************************************
	instantiates needed ArrayLists and gets user input to find
	the file, reads in the file by calling appropriate method,
	decodes the message by taking values from the file and 
	organizing them into arrayLists, and manipulating them based
	on what each undo command and given message contains.
	 *****************************************************************/
	public void runUnMixer() {

		System.out.println("Enter file name to retrieve encrypted message and decrypt it:");
		clipBoard = new ArrayList<Character>();
		commands = new ArrayList<String>();
		temps = new ArrayList<String>();
		Scanner scan = new Scanner(System.in);
		String s = scan.nextLine();
		theFile = s;
		try {
			readMixed(theFile);
		} catch (FileNotFoundException e) {
			System.out.println("No Such File");
			runUnMixer();
		}
		catch (IOException e) {
			System.out.println("Can't do this");
			runUnMixer();
		}

		size = temps.size()-1;
		encryptedMsg = temps.get(size);

		encryptedMsg = encryptedMsg.substring(1, encryptedMsg.length()-3);
		System.out.println("Encrypted message in " +theFile+ ".txt: " + encryptedMsg);
		int sizeOfMsg = encryptedMsg.length();
		theMessage = new LinkedList();
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

		}
		System.out.println("commands: " + commands);

		//for each
		//change first to each
		//must increase position when char is added to list,
		//represented with inc added to pos ....
		int inc = 0;
		int sizeOfIt = commands.size()-1;

		for (int i = commands.size()-1; i >= 0; i--) {
			if (commands.get(i).contains("addR")) {
				String forInt;
				int posToAdd;
				char charToAdd;
				if (commands.get(i).length() == 9) {
					forInt = commands.get(i).substring(5,6);
					posToAdd = Integer.parseInt(forInt);
					charToAdd = commands.get(i).charAt(7);

					if ( i > 0) {
						theMessage.add(posToAdd+inc,charToAdd);
					}
					if (i == 0){
						theMessage.add(posToAdd,charToAdd);
					}
				}

				else if( commands.get(i).length() >9){

					forInt = commands.get(i).substring(5,7);
					posToAdd = Integer.parseInt(forInt);
					charToAdd = commands.get(i).charAt(8);
					if ( i > 0) {	
						theMessage.add(posToAdd+inc,charToAdd);
					}
					if (i == 0){
						theMessage.add(posToAdd,charToAdd);
					}
				}	

			}
			int dec = 0;
			if (commands.get(i).contains("addIb")) {
				String forInt;
				int posToRem;
				if (commands.get(i).length() == 10) {
					forInt = commands.get(i).substring(6,7);
					posToRem = Integer.parseInt(forInt);
					theMessage.remove(posToRem-1);
				}
				else if (commands.get(i).length() > 10) {
					forInt = commands.get(i).substring(6,8);
					posToRem = Integer.parseInt(forInt);
					theMessage.remove(posToRem-1);
				}
			}
			if (commands.get(i).contains("addS")) {

				String forInt;
				int posToRem;
				char charToAdd;
				if (commands.get(i).length() == 9) {
					forInt = commands.get(i).substring(5,6);
					posToRem = Integer.parseInt(forInt);
					charToAdd = commands.get(i).charAt(7);

					if ( i > 0) {

						theMessage.remove(posToRem-dec);
						theMessage.add(posToRem-dec,charToAdd);
						//theMessage.add(posToAdd,charToAdd);
					}
					if (i == 0){

						theMessage.remove(posToRem);
						theMessage.add(posToRem, charToAdd);
					}
				}
				else if( commands.get(i).length() >9) {
					forInt = commands.get(i).substring(5,7);
					posToRem = Integer.parseInt(forInt);
					charToAdd = commands.get(i).charAt(8);

					if ( i > 0) {

						theMessage.remove(posToRem-dec);
						theMessage.add(posToRem-dec,charToAdd);
						//theMessage.add(posToAdd,charToAdd);
					}
					if (i == 0){

						theMessage.remove(posToRem);
						theMessage.add(posToRem, charToAdd);
					}
				}
				//System.out.println(theMessage);

			}
			if (commands.get(i).contains("copy")) {
				String sizeOfCopy;
				int theSize;
				String theChars;
				char[] charArray;
				if (commands.get(i).substring(6, 7).contains(",")){
					sizeOfCopy = commands.get(i).substring(5,6);
					theSize = Integer.parseInt(sizeOfCopy);
					theChars = commands.get(i).substring(7,7+theSize);
					charArray = theChars.toCharArray();

					for (int y = 0; y < theSize; y++) {
						clipBoard.add(charArray[y]);
						sizeOfClipB++;
						setCbSize(sizeOfClipB);
					}
				}
				else if (commands.get(i).substring(7, 8).contains(",")){
					sizeOfCopy = commands.get(i).substring(5,7);
					theSize = Integer.parseInt(sizeOfCopy);
					theChars = commands.get(i).substring(8,8+theSize);
					charArray = theChars.toCharArray();

					for (int y = 0; y < theSize; y++) {
						clipBoard.add(charArray[y]);
						sizeOfClipB++;
						setCbSize(sizeOfClipB);
					}
				}

			}
			if (commands.get(i).contains("cut")) {
				String loc;
				int locToPaste;
				String sizeOfCopy;
				int theSize;
				String theChars;
				char[] charArray;

				if (commands.get(i).substring(5,6).contains(",")
						&& commands.get(i).substring(7,8).contains(",")){
					loc = commands.get(i).substring(4,5);
					locToPaste = Integer.parseInt(loc);
					sizeOfCopy = commands.get(i).substring(6,7);
					theSize = Integer.parseInt(sizeOfCopy);
					theChars = commands.get(i).substring(8,8+theSize);
					charArray = theChars.toCharArray();

					for (int y = 0; y < theSize; y++) {
						clipBoard.add(charArray[y]);	
					}
					for (int z = theSize-1; z >=0; z--){
						theMessage.add(locToPaste - 2,clipBoard.get(z));
					}
				}
				else if (commands.get(i).substring(6,7).contains(",")){
					loc = commands.get(i).substring(4,6);
					locToPaste = Integer.parseInt(loc);
					
					sizeOfCopy = commands.get(i).substring(7,8);
					theSize = Integer.parseInt(sizeOfCopy);
					
					theChars = commands.get(i).substring(9,9+theSize);
					charArray = theChars.toCharArray();

					for (int y = 0; y < theSize; y++) {
						clipBoard.add(charArray[y]);	
					}
					for (int z = theSize-1; z >=0; z--){
						theMessage.add(locToPaste - 2,clipBoard.get(z));
					}	
				}
					else if (commands.get(i).substring(6,7).contains(",")){
						loc = commands.get(i).substring(4,6);
						locToPaste = Integer.parseInt(loc);
						
						sizeOfCopy = commands.get(i).substring(7,8);
						theSize = Integer.parseInt(sizeOfCopy);
						
						theChars = commands.get(i).substring(9,9+theSize);
						charArray = theChars.toCharArray();

						for (int y = 0; y < theSize; y++) {
							clipBoard.add(charArray[y]);	
						}
						for (int z = theSize-1; z >=0; z--){
							theMessage.add(locToPaste - 2,clipBoard.get(z));
						}		
			}
		}
		if (commands.get(i).contains("paste")) {

			//have to remove same element theLen times

			int locToUnpaste;
			int theLen;
			if (commands.get(i).length()< 11) {

				String loc = commands.get(i).substring(6,7);
				locToUnpaste = Integer.parseInt(loc);
				String len = commands.get(i).substring(8,9);
				theLen = Integer.parseInt(len);
			}
			else {
				String loc = commands.get(i).substring(6,8);
				locToUnpaste = Integer.parseInt(loc);
				String len = commands.get(i).substring(9,10);
				theLen = Integer.parseInt(len);
			}

			int g = 0;
			while (g < theLen){

				//System.out.println(g);

				g++;
				theMessage.remove(locToUnpaste-1);
				clipBoard.clear();
			}
		}
		//cut is where i need to use what is on the clipBoard.
		//cut means to add the clipboard to the list
	}
	System.out.println("clipBoard: " + clipBoard);
	//	System.out.println(theMessage);
	//convert linkedlist to a final message
	char[] array = new char[theMessage.size()];
	for (int i = 0; i < theMessage.size(); i++) {
		array[i] = (char) theMessage.get(i);
	}
	String finalString = new String(array);
	System.out.println("The original Message was: " +finalString);
	UnMixUsingFile(finalString);
	finalMessage = finalString;
}


/*****************************************************************
	sets the clipBoard size
	@param size to be set
 *****************************************************************/
public void setCbSize(int size) {

	sizeOfClipB = size;
}


/*****************************************************************
	gets the size of clipboard
	@return the size of the clipboard
 *****************************************************************/
public int getCbSize() {

	return sizeOfClipB;
}



/*****************************************************************
	reads the file given by user input from scanner
	@param theFile si the name of the file given by the user
 *****************************************************************/
public void readMixed(String theFile) throws FileNotFoundException {

	//if thefile is wrong file name, no such file will be found
	String each = "";
	Scanner inFile1 = new Scanner(new File(theFile + ".txt")).useDelimiter(",");

	while (inFile1.hasNext()) {
		each = inFile1.next();
		temps.add(each);

	}
	inFile1.close();

}
}