package proj4;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

public class Testing {

	@Test
	public void testMultipleInput1() {

		Mix m = new Mix();
		m.setInitialMessage("messagehere");	
		m.processCommand("r 1");
		m.processCommand("r 2");
		m.processCommand("Q");
		System.out.println(m.finalMsg);
		assertEquals(m.finalMsg, "esagehere");
	}

	@Test
	public void testMultipleInput2() {

		Mix m = new Mix();
		m.setInitialMessage("messagehere");	
		m.processCommand("b t 1");
		m.processCommand("w 1 g");
		m.processCommand("Q");
		System.out.println(m.finalMsg);
		assertEquals(m.finalMsg, "gmessagehere");

	}
	@Test
	public void testMultipleInput3() {

		Mix m = new Mix();
		m.setInitialMessage("messagehere");	
		m.processCommand("r 5");
		m.processCommand("w 8 h");
		m.processCommand("r 2");
		m.processCommand("Q");
		System.out.println(m.finalMsg);
		assertEquals(m.finalMsg, "mssgehhre");

	}
	@Test(expected=IllegalArgumentException.class)
	public void testMultipleInput4() {

		Mix m = new Mix();
		m.setInitialMessage("messagehere");	
		m.processCommand("r 5");
		m.processCommand("w 8 h");
		m.processCommand("kljdsfkljsadbfa");
		m.processCommand("r 2");
		m.processCommand("Q");
		System.out.println(m.finalMsg);
		assertEquals(m.finalMsg, "mssgehhre");

	}

	@Test(expected=IllegalArgumentException.class)
	public void RandomUserInput() {

		Mix m = new Mix();
		m.setInitialMessage("message");
		m.processCommand("1212");


	}
	@Test(expected=IllegalArgumentException.class)
	public void RandomUserInput2() {

		Mix m = new Mix();
		m.setInitialMessage("message");
		m.processCommand("4747474");

	}

	@Test(expected=IllegalArgumentException.class)
	public void RandomUserInput3() {

		Mix m = new Mix();
		m.setInitialMessage("message");
		m.processCommand("-888");


	}

	@Test
	public void testCommandsRemove() {

		Mix m = new Mix();
		m.setInitialMessage("message");	
		m.processCommand("r 5");
		m.processCommand("r 5");
		m.processCommand("r 5");
		m.processCommand("Q");
		assertEquals(m.finalMsg, "mess");
	}
	@Test
	public void testCommandsRemove1() {

		Mix m = new Mix();
		m.setInitialMessage("message");	
		m.processCommand("r 5555");
		m.processCommand("Q");
		assertEquals(m.finalMsg, "message");
	}
	@Test
	public void testCommandsRemove3() {

		Mix m = new Mix();
		m.setInitialMessage("message");	
		m.processCommand("rrrr 1");
		m.processCommand("Q");
		assertEquals(m.finalMsg, "essage");
	}
	@Test
	public void testCommandsRemove2() {

		Mix m = new Mix();
		m.setInitialMessage("message");	
		m.processCommand("rrrr 12");
		m.processCommand("Q");
		assertEquals(m.finalMsg, "message");
	}
	@Test
	public void testCommandsInsertBefore() {

		Mix m = new Mix();
		m.setInitialMessage("message");	
		m.processCommand("b t 5");
		m.processCommand("Q");
		assertEquals(m.finalMsg, "messtage");
	}
	@Test
	public void testCommandsInsertBefore2() {

		Mix m = new Mix();
		m.setInitialMessage("message");	
		m.processCommand("b t 5555");
		m.processCommand("Q");
		assertEquals(m.finalMsg, "message");
	}
	@Test
	public void testCommandsInsertBefore3() {

		Mix m = new Mix();
		m.setInitialMessage("message");	
		m.processCommand("b tasdlkn 5555");
		m.processCommand("Q");
		assertEquals(m.finalMsg, "message");
	}
	@Test
	public void testCommandsSwitch() {

		Mix m = new Mix();
		m.setInitialMessage("message");	
		m.processCommand("w 5 t");
		m.processCommand("Q");
		assertEquals(m.finalMsg, "messtge");
	}
	@Test
	public void testCommandsSwitch5() {

		Mix m = new Mix();
		m.setInitialMessage("message");	
		m.processCommand("wwwww 5 t");
		m.processCommand("Q");
		assertEquals(m.finalMsg, "messwge");
	}
	@Test
	public void testCommandsSwitch1() {

		Mix m = new Mix();
		m.setInitialMessage("message");	
		m.processCommand("w 555555 t");
		m.processCommand("Q");
		assertEquals(m.finalMsg, "message");
	}
	@Test
	public void testCommandsSwitch2() {

		Mix m = new Mix();
		m.setInitialMessage("message");	
		m.processCommand("w 5 tttkjka");
		m.processCommand("Q");
		assertEquals(m.finalMsg, "messtge");
	}
	@Test
	public void testCommandsSwitch3() {

		Mix m = new Mix();
		m.setInitialMessage("message");	
		m.processCommand("w -83 tttkjka");
		m.processCommand("Q");
		assertEquals(m.finalMsg, "message");
	}
	@Test
	public void testCommandsSwitch4() {

		Mix m = new Mix();
		m.setInitialMessage("message");	
		m.processCommand("w 99 ksda");
		m.processCommand("Q");
		assertEquals(m.finalMsg, "message");
	}



	@Test
	public void testRandomCommands() {

		Mix m = new Mix();
		m.setInitialMessage("message");	
		m.processCommand("w 2 t");
		m.processCommand("Q");
		assertEquals(m.finalMsg, "mtssage");

	}
	@Test
	public void testRandomCommands1() {

		Mix m = new Mix();
		m.setInitialMessage("message");	
		m.processCommand("r 2 t");
		m.processCommand("Q");
		assertEquals(m.finalMsg, "mssage");

	}
	@Test
	public void testRandomCommands2() {

		Mix m = new Mix();
		m.setInitialMessage("message");	
		m.processCommand("b t 1");
		m.processCommand("Q");

		assertEquals(m.finalMsg, "tmessage");

	}
	@Test
	public void testInitalMsg() {

		Mix m = new Mix();
		m.setInitialMessage("mess");
		m.processCommand("Q");
		assertEquals(m.finalMsg, "mess");

	}
	@Test
	public void testPasteCutCopy() {

		Mix m = new Mix();
		m.setInitialMessage("message");
		m.processCommand("x 1 3");
		m.processCommand("p 1");
		m.processCommand("Q");
		assertEquals(m.finalMsg, "message");

	}
	@Test
	public void testPasteCutCopy1() {

		Mix m = new Mix();
		m.setInitialMessage("message");
		m.processCommand("c 1 3");
		m.processCommand("p 1");
		m.processCommand("Q");
		System.out.println(m.finalMsg);
		assertEquals(m.finalMsg, "mesmessage");

	}
	@Test
	public void testPasteCutCopy2() {

		Mix m = new Mix();
		m.setInitialMessage("message");
		m.processCommand("c 1 4");
		m.processCommand("p 1");
		m.processCommand("Q");
		System.out.println(m.finalMsg);
		assertEquals(m.finalMsg, "messmessage");

	}
	@Test
	public void testPasteCutCopy3() {

		Mix m = new Mix();
		m.setInitialMessage("message");
		m.processCommand("c 1 3");
		m.processCommand("p 22");
		m.processCommand("Q");
		System.out.println(m.finalMsg);
		assertEquals(m.finalMsg, "message");

	}
	@Test
	public void testPasteCutCopy4() {

		Mix m = new Mix();
		m.setInitialMessage("message");
		m.processCommand("x 3 9377");
		m.processCommand("p 22");
		m.processCommand("Q");
		System.out.println(m.finalMsg);
		assertEquals(m.finalMsg, "message");

	}


	public void BadUserInput() {

		Mix m = new Mix();
		m.setInitialMessage("message");
		m.processCommand("q 33");
		System.out.println(m.finalMsg);

	}
	@Test(expected=IllegalArgumentException.class)
	public void BadUserInput1() {

		Mix m = new Mix();
		m.setInitialMessage("message");
		m.processCommand("kl ");

	}
	@Test(expected=IllegalArgumentException.class)
	public void BadUserInput2() {

		Mix m = new Mix();
		m.setInitialMessage("message");
		m.processCommand("n 55");
		System.out.println(m.finalMsg);

	}
	@Test(expected=StringIndexOutOfBoundsException.class)
	public void BadUserInput3() {

		Mix m = new Mix();
		m.setInitialMessage("message");
		m.processCommand("s");
	}
	@Test(expected=StringIndexOutOfBoundsException.class)
	public void BadUserInput4() {

		Mix m = new Mix();
		m.setInitialMessage("message");
		m.processCommand("");
	}
	@Test(expected=IllegalArgumentException.class)
	public void BadUserInput5() {

		Mix m = new Mix();
		m.setInitialMessage("message");
		m.processCommand("m 5");
	}
	@Test(expected=IllegalArgumentException.class)
	public void BadUserInput6() {

		Mix m = new Mix();
		m.setInitialMessage("message");
		m.processCommand("t");
	}
	@Test(expected=IllegalArgumentException.class)
	public void BadUserInput7() {

		Mix m = new Mix();
		m.setInitialMessage("message");
		m.processCommand("12");
	}

	@Test
	//@Test(expected=NullPointerException.class)
	public void testCommandsOutSideRange() {

		Mix m = new Mix();
		m.setInitialMessage("mess");	
		m.processCommand("x 5 8");
		m.processCommand("Q");

		assertEquals(m.finalMsg, "mess");

	}
	@Test
	//@Test(expected=NumberFormatException.class)
	public void testCommandsOutSidePos() {

		Mix m = new Mix();
		m.setInitialMessage("mess");	
		m.processCommand("r ---");
		m.processCommand("Q");
		assertEquals(m.finalMsg, "mess");

	}

	@Test
	//@Test(expected=NullPointerException.class)
	public void testCommandsOutSidePos1() {

		Mix m = new Mix();
		m.setInitialMessage("mess");	
		m.processCommand("r 66");
		m.processCommand("Q");
		assertEquals(m.finalMsg, "mess");

	}
	@Test
	//@Test(expected=NullPointerException.class)
	public void testCommandsOutSidePos2() {

		Mix m = new Mix();
		m.setInitialMessage("mess");	
		m.processCommand("r -17");
		m.processCommand("Q");
		assertEquals(m.finalMsg, "mess");

	}
	@Test
	//@Test(expected=NullPointerException.class)
	public void testCommandsOutSidePos3() {

		Mix m = new Mix();
		m.setInitialMessage("mess");	
		m.processCommand("b -3 t");
		m.processCommand("Q");
		assertEquals(m.finalMsg, "mess");

	}
	@Test
	//@Test(expected=NullPointerException.class)
	public void testCommandsOutSidePos4() {

		Mix m = new Mix();
		m.setInitialMessage("mess");	
		m.processCommand("b 99 t");
		m.processCommand("Q");
		assertEquals(m.finalMsg, "mess");

	}
	@Test
	//@Test(expected=NullPointerException.class)
	public void testCommandsOutSidePos5() {

		Mix m = new Mix();
		m.setInitialMessage("mess");	
		m.processCommand("w 73 t");
		m.processCommand("Q");
		assertEquals(m.finalMsg, "mess");

	}
	@Test
	public void testCommandsOutSidePos6() {

		Mix m = new Mix();
		m.setInitialMessage("mess");	
		m.processCommand("w -55 t");
		m.processCommand("Q");
		assertEquals(m.finalMsg, "mess");

	}
	@Test
	public void testMultipleInput() {

		Mix m = new Mix();
		m.setInitialMessage("mess");	
		m.processCommand("w -55 t");
		m.processCommand("r 1");
		m.processCommand("Q");
		assertEquals(m.finalMsg, "ess");
	}

	//                     UNMIX

	@Test(expected=FileNotFoundException.class)
	public void testInputs2() throws FileNotFoundException {

		Mix m = new Mix();
		m.setInitialMessage("message");
		m.processCommand("r 1");
		m.processCommand("Q");	
		m.processCommand("s Key5555");
		UnMix u = new UnMix();
		u.readMixed("randomfilename");
		u.runUnMixer();
		assertEquals(u.finalMessage, "message");
	}


}
