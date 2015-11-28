package proj4;

public class Link {

	public char letter;
	public Link next;

	public Link(char letter) {

		this.letter = letter;	

	}
	public void displaySpace() {

		System.out.print(" " + letter + " ");
	}
	public void display() {

		System.out.print(letter+ "  ");
	}

}
