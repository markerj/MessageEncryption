/*****************************************************************
Manipulates values of two created linked lists, storing chars, 
and respective numbers for each char. Encrypts a message entered
by the user to be decryped after values from this class are
passed to an Unmix class to be manipulated further. 

@author John Marker
@version Fall 2015
 *****************************************************************/
package proj4;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Mix implements IMix {

	/** linkedlist holding chars */
	private LinkedList message;

	/** linkedList holding numbers */
	private LinkedListNum numbers;

	/** char array holding the message */
	public char[] charMsg;

	/** int array holding the message numbers */
	public int[] nums;

	/** boolean representing if program is running */
	public boolean mixing = true;

	/** boolean representing if message is saved */
	public boolean isSaved = false;

	/** boolean representing if message is pasted */
	public boolean isPasting = false;

	/** boolean representing if paste used Insert before */
	public boolean isPastingInIb = false;

	/** boolean representing if message has been cut */
	public boolean isCutting = false;

	/** boolean reprenting if the program has quit*/
	public boolean quit = false;
	
	/** boolean reprenting if there is a new mixer*/
	public boolean newMixer = false;

	/** holds value of the final message*/
	String finalMsg;

	/** Holds the commands to undo the message */
	ArrayList<String> elements;

	/** a clipboard of copied chars */
	ArrayList<Character> copiedChars;

	/*****************************************************************
	encapsulation needs to be fixed
	 *****************************************************************/

	/*****************************************************************
	Creates a new mix, and calls the runMixer method
	 *****************************************************************/
	public static void main(String[] args) {

		Mix Prog = new Mix();
		Prog.runMixer();	
		
	}
	public Mix() {
	
	}

	/*****************************************************************
	Runs the entire program. Obtains user input, processes the input, 
	manipulates data from linkedlist based on the input
	 *****************************************************************/
	public void runMixer() {
		
		elements = new ArrayList<>();
		//clipBoard = new ArrayList<>();
		//commandsArray = new String[50];
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Enter message to be encrypted");
		
		String s = scan.nextLine();
		setInitialMessage(s);
		System.out.println();
		int i =0;
		while (mixing == true){
			System.out.print("command: ");
			String s1 = scan.nextLine();

			//booleans needed to seperate cutting and pasting
			//from removing and inserting
			if (isPastingInIb != true && isCutting != true){
				elements.add(s1);
			}
		
			processCommand(s1);
			i++;
			createNumsLL();

			//booleans needed to stop user input
			//when the user has quit and saved the message
			if(isSaved == false && quit == false) {
				numbers.displayNum();
				System.out.println();
				message.display();
				System.out.println();
			}
		}
	}


	/*****************************************************************
	Removes the char at the indicated position
	@param pos represents the position for the char to be removed at
	 *****************************************************************/
	public void removeAtPosition(int pos) {

		char charToAdd = message.get(pos);
		char lpar = '(';
		char rpar = ')';

		String undoCmd = ("addR" + lpar + pos + "|" + charToAdd + rpar);
		if(isCutting == false) {
			elements.add(undoCmd);
		}
		message.remove(pos);
	}


	/*****************************************************************
	stops the message from being manipulated futher, 
	final message is saved here
	 *****************************************************************/
	public void quit() {

		//save what is being displayed finally
		int size = getSize();
		char[] chars = new char[size];
		for(int i = 0; i < size; i++) {
			chars[i] = getChar(i);
		}
		String finMsg = new String(chars);
		finalMsg = finMsg;
		System.out.println("Final encrypted message:" + finalMsg);
		quit = true;
	}


	/*****************************************************************
	Gets the size of the message
	@return the size of the message in the linked list
	 *****************************************************************/
	public int getSize() {

		return message.getSize();
	}


	/*****************************************************************
	Find char at the indicated pos
	@param pos indicated to find char at 
	@return char at indicated pos
	 *****************************************************************/
	public char getChar(int pos) {

		return message.get(pos);
	}


	/*****************************************************************
	Inserts a char c into the linkedlist, before the position pos
	@param pos position for char to be inserted
	@param c char to be inserted
	 *****************************************************************/
	public void insertBefore(char c, int pos) {

		char lpar = '(';
		char rpar = ')';
		String undoCmd = ("addIb" + lpar + pos + "|" + c + rpar);
		if(isPastingInIb == false) {
			elements.add(undoCmd);
		}
		message.insertBeforeIndex(c, pos-1);
	}


	/*****************************************************************
	Switches the char indicated at position with char c
	@param pos to find char to be switched
	@param c to replace old char with new char
	 *****************************************************************/
	public void switchAt(int pos, char c) {

		char lpar = '(';
		char rpar = ')';
		char oldChar = message.get(pos);
		String undoCmd = ("addS" + lpar + pos + "|" + oldChar + rpar);
		elements.add(undoCmd);
		message.switchIt(pos, c);

		//have to get element at pos and then pass that to undo
	}


	/*****************************************************************
	cut into an array of chars to save those values, they may be
	pasted if user requests it
	@param pos starting position to cut
	@param pos2 ending position to cut
	 *****************************************************************/
	public void cut(int pos, int pos2) {

		isCutting(true);
		copiedChars = new ArrayList<>();
		int i = pos-1;
		while (i < pos2){
			copiedChars.add(getChar(i));
			i++;
		}
		int i1 = pos;
		while (i1 < pos2+1) {
			removeAtPosition(pos-1);
			i1++;
		}
		char lpar = '(';
		char rpar = ')';

		//pass copied chars for undo, have to pass starting pos as well
		char[] charsUndo = new char[copiedChars.size()];
		for (int y = 0; y < copiedChars.size(); y ++) {		
			charsUndo[y] = copiedChars.get(y);
		}

		//convert to a string to remove delims ....
		String intoString = new String(charsUndo);
		int sizeOfString = intoString.length();
		int pos3 = pos +1;
		String undoCmd = ("cut" + lpar + pos3 + "|" + sizeOfString + "|" + intoString+ rpar);
		elements.add(undoCmd);
		isCutting(false);
	}


	/*****************************************************************
	Copies at the indicated indices an array of chars so that they
	may be pasted later if the user desires
	@param pos starting position to copy
	@param pos2 ending position to copy
	 *****************************************************************/
	public void copy(int pos, int pos2) {

		//how long to parse through list
		int length = (pos2 - pos)+1;
		copiedChars = new ArrayList<>();
		int i = pos-1;
		while (i < pos2){
			copiedChars.add(getChar(i));
			i++;
		}
		char lpar = '(';
		char rpar = ')';

		//pass copied chars for undo, have to pass starting pos as well
		char[] charsUndo = new char[copiedChars.size()];
		for (int y = 0; y < copiedChars.size(); y ++) {		
			charsUndo[y] = copiedChars.get(y);
		}

		//convert to a string to remove delims ....
		String intoString = new String(charsUndo);
		int sizeOfString = intoString.length();
		String undoCmd = ("copy" + lpar + sizeOfString + "|" + 
				intoString + rpar);
		elements.add(undoCmd);
	}


	/*****************************************************************
	Sets a boolean value as either true or false to determine if
	user is cutting or not
	@param bool sets variable to true or false
	 *****************************************************************/
	public void isCutting(boolean bool) {

		if (bool == true)
		{
			isCutting = true;
		}
		if (bool == false) {
			isCutting = false;
		}
	}


	/*****************************************************************
	Sets a boolean value as either true or false to determine if
	user is pasting using insert or not
	@param bool sets variable to true or false
	 *****************************************************************/
	public void isPastingIb(boolean bool) {

		if (bool == true)
		{
			isPastingInIb = true;
		}
		if (bool == false) {
			isPastingInIb = false;
		}
	}


	/*****************************************************************
	pastes chars to the linked list that have been previously saved
	@param pos gives starting position to begin pasting copiedchars
	 *****************************************************************/
	public void paste(int pos) {

		//isPasting = true;
		isPastingIb(true);
		//int i = 0;
		int i2 = copiedChars.size();
		while(i2 > 0){

			//insert same position ....
			//going in backwards because "insert before" ....
			i2--;
			insertBefore(copiedChars.get(i2), pos);
		}
		char lpar = '(';
		char rpar = ')';

		//maybe pass clip size from here
		int lenOC= copiedChars.size();
		String lengthOfCopy = Integer.toString(lenOC);
		String undoCmd = ("paste" + lpar + pos + "|" + lengthOfCopy + rpar);
		elements.add(undoCmd);
		isPastingIb(false);
	}


	/*****************************************************************
	Processes the user input. Based on what the user has entered, 
	the aforementioned methods will be called to manipulated
	the linked list.
	@param command the user input
	 *****************************************************************/
	@Override
	public void processCommand(String command) {

		//paste from saved charsArray ....
		if(command.substring(0,1).contains("p")){
			String cmd[] = command.split(" ");
			String cmd2 = cmd[1];
			int position1 = Integer.parseInt(cmd2);
			paste(position1);
		}

		//cut from list, save from pos to pos "x p p" ....
		if(command.substring(0,1).contains("x")){
			String cmd[] = command.split(" ");
			String cmd2 = cmd[1];
			String cmd3 = cmd[2];
			int position1 = Integer.parseInt(cmd2);
			int position2 = Integer.parseInt(cmd3);
			cut(position1, position2);
		}

		//copy from pos to pos. "c p p" ....
		if(command.substring(0,1).contains("c")){
			String cmd[] = command.split(" ");
			String cmd2 = cmd[1];
			String cmd3 = cmd[2];
			int position1 = Integer.parseInt(cmd2);
			int position2 = Integer.parseInt(cmd3);
			copy(position1, position2);
		}

		//quit mixing ....
		if(command.substring(0,1).contains("Q")){
			quit();
		}

		// Remove a char at indicated position 
		// note: pos-1 ....
		if(command.substring(0,1).contains("r"))
		{
			String cmd[] = command.split(" ");
			String cmd2 = cmd[1];
			int pos = Integer.parseInt(cmd2);
			removeAtPosition(pos -1);
		}

		//insert char 'c' before indicated pos ....
		if(command.substring(0,1).contains("b"))
		{
			char c = command.charAt(2);
			String cmd[] = command.split(" ");
			String cmd3 = cmd[2];
			int pos = Integer.parseInt(cmd3);
			insertBefore(c, pos-1);
		}

		//switch char out at indicated pos ....
		if(command.substring(0,1).contains("w"))
		{
			char c = command.charAt(4);
			String cmd[] = command.split(" ");
			String cmd2 = cmd[1];
			int pos = Integer.parseInt(cmd2);
			switchAt(pos-1,c);
		}

		//save to text file ....
		if(command.substring(0,1).contains("s"))
		{
			//save commands, and final mixed msg
			String fileN = command.substring(2,command.length());
			saveToText(fileN);
		}
	}


	/*****************************************************************
	Saves the message and commands to undo the message to a text file
	@param fileName the name of the file given by user input
	 *****************************************************************/
	public void saveToText(String fileName) {

		try
		{
			PrintWriter pr = new PrintWriter(fileName+".txt");    
			elements.remove(elements.size()-1);
			elements.remove(elements.size()-1);
			for(int i = 0; i < elements.size()-1; i++){
				elements.remove(elements.get(i));
			}
			elements.add(finalMsg);
			pr.println(elements);
			pr.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		mixing = false;
		isSaved = true;
		System.out.println("Encrypted Message and Commands saved to "+"Key"+".txt");
	}


	/*****************************************************************
	Gets the inital user input to set up the first message into the
	linked list, to be manipulated by latter user inputs
	@param message1 the original user input to set message
	 *****************************************************************/
	@Override
	public void setInitialMessage(String message1) {

		//after every process command get length of message, 0 to length for
		//numbers . should only need add command
		int msgLength = message1.length();
		int size2 = msgLength + 1;
		charMsg = new char[size2];
		nums = new int[size2];
		for (int i = 0; i < size2; i++) {
			nums[i] = i;
		}
		charMsg = message1.toCharArray();
		numbers = new LinkedListNum();
		for (int i = 1; i < size2; i++) {
			numbers.add(nums[i]);
		}
		System.out.println("Message:");
		message = new LinkedList();
		for(int i = 0; i < msgLength; i++) {
			message.add(charMsg[i]);
		}
		numbers.displayNum();
		System.out.println();
		message.display();
	}


	/*****************************************************************
	Creates a numbers linked list
	 *****************************************************************/
	public void createNumsLL() {

		int size = message.getSize()+1;
		nums = new int[size];
		for (int i = 0; i < size; i++) {
			nums[i] = i;
		}
		numbers = new LinkedListNum();
		for (int i = 1; i < size; i++) {
			numbers.add(nums[i]);
		}
	}
}