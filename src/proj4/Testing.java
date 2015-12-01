package proj4;

import static org.junit.Assert.*;

import org.junit.Test;

public class Testing {

	@Test
	public void testCommandsRemove() {
		
		Mix m = new Mix();
		m.setInitialMessage("message");	
		m.processCommand("b t 6");
		
		
	}

}
