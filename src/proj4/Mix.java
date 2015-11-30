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

	private LinkedList message;
	private LinkedListNum numbers;
	public char[] charMsg;
	public int[] nums;
	public boolean mixing = true;
	public boolean isSaved = false;
	public boolean isPasting = false;
	public boolean isPastingInIb = false;
	public boolean isCutting = false;
	//String[] commandsArray;
	//ArrayList<String> clipBoard;
	String finalMsg;
	ArrayList<String> elements;
	ArrayList<Character> copiedChars;

	public static void main(String[] args) {

		Mix Prog = new Mix();
		Prog.runMixer();

	}
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
			if (isPastingInIb != true){
			elements.add(s1);
			}
			
			System.out.println("in the mixer method: " + elements);
			//clipBoard.add(s1);
			//commandsArray[i] = s1;

			processCommand(s1);
			i++;
			createNumsLL();
			if(isSaved == false) {
				numbers.displayNum();
				System.out.println();
				message.display();
				System.out.println();
			}
			System.out.println("in the mixer method: " + elements);

		}
	}
	public void removeAtPosition(int pos) {

		char charToAdd = message.get(pos);
		char lpar = '(';
		char rpar = ')';

		String undoCmd = ("addR" + lpar + pos + "|" + charToAdd + rpar);

		elements.add(undoCmd);
		message.remove(pos);

		System.out.println("in the remove method: " + elements);

	}

	public void quit() {
		//save what is being displayed finally

		int size = getSize();
		char[] chars = new char[size];
		for(int i = 0; i < size; i++) {
			chars[i] = getChar(i);

		}
		String finMsg = new String(chars);
		finalMsg = finMsg;

		//System.out.println(finMsg);

		System.out.println("Final encrypted message:");
		//message.display();
	}

	public int getSize() {
		//message.getSize();
		return message.getSize();
	}

	public char getChar(int pos) {

		return message.get(pos);
	}
	public void insertBefore(char c, int pos) {
		
		//char charToAdd = message.get(pos);
		char lpar = '(';
		char rpar = ')';

		String undoCmd = ("addIb" + lpar + pos + "|" + c + rpar);
		if(isPastingInIb == false) {
		elements.add(undoCmd);
		
		}
		
		message.insertBeforeIndex(c, pos-1);
		
		System.out.println("in the insert method: " + elements);
	
	}

	public void switchAt(int pos, char c) {

		char lpar = '(';
		char rpar = ')';
		char oldChar = message.get(pos);
		System.out.println("oldChar:" + oldChar);
		String undoCmd = ("addS" + lpar + pos + "|" + oldChar + rpar);

		elements.add(undoCmd);
		message.switchIt(pos, c);
		//have to get element at pos and then pass that to undo
		System.out.println("in the switch method: " + elements);
		//message.display();
		//System.out.println();
	}
	public void cut(int pos, int pos2) {

		copiedChars = new ArrayList<>();
		int i = pos-1;
		while (i < pos2){

			copiedChars.add(getChar(i));
			i++;
		}
		//passed 5, 8
		int i1 = pos;
		//i1 = 5
		//pos2 = 8
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
		
		String undoCmd = ("copy" + lpar + pos + "|" + intoString + rpar);
		elements.add(undoCmd);

		//message.display();

		System.out.println("clipBoard: " +copiedChars);
	}
	public void copy(int pos, int pos2) {
		//how long to parse through list
		int length = (pos2 - pos)+1;
		//char[] copiedChars = new char[length];
		copiedChars = new ArrayList<>();
		int i = pos-1;
		while (i < pos2){
			copiedChars.add(getChar(i));
			i++;
		}
		System.out.println("clipBoard: " +copiedChars);
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
		String undoCmd = ("copy" + lpar + sizeOfString + "|" + intoString + rpar);
		elements.add(undoCmd);

	}
	public void isCutting(boolean bool) {
		if (bool == true)
		{
			isCutting = true;
		}
		if (bool == false) {
		isCutting = false;
		}
	}
	public void isPastingIb(boolean bool) {
		if (bool == true)
		{
			isPastingInIb = true;
		}
		if (bool == false) {
		isPastingInIb = false;
		}
	}
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
			System.out.println("its writing if list here: " +elements);
			pr.println(elements);

			//pr.println(finalMsg);
			pr.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();

		}
		mixing = false;
		isSaved = true;
		System.out.println("Mixed Message and Commands saved to "+"Key"+".txt");
	}

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