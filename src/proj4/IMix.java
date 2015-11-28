package proj4;

public interface IMix {

	   /** processes the given mix command */
	   void processCommand(String command);
		
	   /** set the original message */
	   void setInitialMessage(String message);

	}