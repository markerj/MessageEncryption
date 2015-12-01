package proj4;

public interface IMix {

	   /** processes the given mix command 
	 * @return */
	   void processCommand(String command);
		
	   /** set the original message */
	   void setInitialMessage(String message);

	}