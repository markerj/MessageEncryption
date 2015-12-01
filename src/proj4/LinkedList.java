/*****************************************************************
Custom linked list that can adjust a list of characters in
whichever way the mix class requires

@author John Marker
@version Fall 2015
 *****************************************************************/
package proj4;

public class LinkedList {

	/** A Link pointing to top of list */
	public Link top;

	/** A Link pointing to bottom of list */
	public Link bottom;


	/*****************************************************************
	Empty constructor that initializes the top and bottom pointers 
	to point to nothing
	 *****************************************************************/
	LinkedList() {
		
		top = null;
		bottom = null;
	}


	/*****************************************************************
	appends a new character to the end of the list. Every time a char
	is added, the bottom pointer points to that char
	@param letter the new char to be added
	 *****************************************************************/
	public void add(char letter) {
		
		Link newLink = new Link(letter);
		if (bottom == null){
			top = newLink;
			bottom = newLink;
		}

		bottom.next = newLink;
		bottom = newLink;
	}


	/*****************************************************************
	Placeholder
	 *****************************************************************/
	public Link delete() {
		
		return null;
	}


	/*****************************************************************
	parses through the list and finds the char at given index 
	@param index to finds the char
	@return the char at the index
	 *****************************************************************/
	public char get(int index) {

		Link current = top;
		if(index ==0) {
			return current.letter;
		}
		for (int i = 0; i < index -1; i++) {
			current = current.next;
		}
		return current.next.letter;
	}

	public int getSize() {

		int count=0;
		Link current = top;

		while(current != null){
			count++;
			current=current.next;
		} 
		return count;

	}


	/*****************************************************************
	parses through list, finds link before desired link specified 
	by the index, jumps pointer over the indexed link
	@param index at which the char should be removed
	 *****************************************************************/
	public void remove(int index) {

		if (index == 0) {
			// point past first link if top is the index
			top = top.next;
		} else {
			// parse through list,
			// find link before the one we wish to remove, index-1
			// find desired link to jump
			Link current = top;
			for (int i = 0; i < index - 1; i++) {
				current = current.next;
			}

			// jump pointer over link
			current.next = current.next.next;
		}
	}


	/*****************************************************************
	Inserts a cahr before the given index by going to link before
	desired, inserting new char
	@param c char to be placed before index
	@param index for char to be placed before
	 *****************************************************************/
	public void insertBeforeIndex(char c, int index) {

		Link current = top;
		int indexCounter = 0;

		// go to link before desired link
		int theIndex = index-1;

		Link link = new Link(c);

		if (current != null) {

			while (indexCounter < theIndex && current.next != null) {

				indexCounter++;
				current= current.next;
			}
			if (indexCounter == theIndex) {

				link.next = current.next;
				current.next = link;
			} 
			else if (index == 0) {

				link.next = top;
				top = link;
			}
		} 
		else if (index == 0) {

			top = link;
		}     
	}


	/*****************************************************************
	switches the char at the given index with the new char given
	remove method takes away char at given index, insertBeforeIndex
	inserts given char at given index
	@param c char to be inserted in place of char at given index char
	@param index to find char to be replaced
	 *****************************************************************/
	public void switchIt(int index, char c) {

		remove(index);
		insertBeforeIndex(c, index);
	}


	/*****************************************************************
	displays the entire list by parsing through list until the last
	link isn't pointing to anything
	 *****************************************************************/
	public void display() {

		Link aLink = top;
		while(aLink != null) {
			{
				aLink.display();

				aLink = aLink.next;
			}		
		}
	}
}