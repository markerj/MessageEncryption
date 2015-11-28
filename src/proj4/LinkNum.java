package proj4;

public class LinkNum {

	public int num;

	public LinkNum next;

	public LinkNum(int num){
		this.num = num;

	}

	public void displayNum() {

		System.out.print(num + "  ");
	}

	public void displayNumSpace() {

		System.out.print(num + " ");
	}


}