package proj4;

public class LinkedList {

	public Link top;
	public Link bottom;

	LinkedList() {
		top = null;
		bottom = null;
	}

	public void add(char letter) {
		Link newLink = new Link(letter);
		if (bottom == null){
			top = newLink;
			bottom = newLink;
		}

		bottom.next = newLink;
		bottom = newLink;
	}

	public Link delete() {
		return null;
	}

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
	public void remove(int index) {

		if (index == 0) {
			// point past first link if top is the index
			top = top.next;
		} else {
			// look through list,
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

	public void switchIt(int index, char c) {
		remove(index);
		insertBeforeIndex(c, index);

	}

	public void display() {
		Link aLink = top;
		while(aLink != null) {
			{
					aLink.display();
			
			aLink = aLink.next;
		}}
}}

