package proj4;

public class LinkedListNum {

	public LinkNum top;
	public LinkNum bottom;

	LinkedListNum() {
		top = null;
		bottom = null;
	}

	public void add(int num) {
		LinkNum newLink = new LinkNum(num);
		if (bottom == null){
			top = newLink;
			bottom = newLink;
		}

		bottom.next = newLink;
		bottom = newLink;
	}

	public LinkNum delete() {
		return null;
	}

	public int get(int index) {
		
		LinkNum current = top;
		if(index ==0) {
			return current.num;
		}
		for (int i = 0; i < index -1; i++) {
			current = current.next;
		}
		return current.next.num;
	}

	public int getSize() {

		int count=0;
		LinkNum current = top;

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
			LinkNum current = top;
			for (int i = 0; i < index - 1; i++) {
				current = current.next;
			}

			// jump pointer over link
			current.next = current.next.next;
		}
	}
	public void insertBeforeIndex(int c, int index) {

		LinkNum current = top;
		int indexCounter = 0;

		// go to link before desired link
		int theIndex = index-1;

		LinkNum link = new LinkNum(c);

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

	public void switchIt(int index, int c) {
		remove(index);
		insertBeforeIndex(c, index);

	}

	public void displayNum() {
		LinkNum aLink = top;
		int i = 0;
		while(aLink != null) {
			{
				
				if (i < 9) {
					aLink.displayNum();
				}
				if (i >=9) {
					aLink.displayNumSpace();
				}
				i++;
			}
			aLink = aLink.next;
	
		}
		}

}

