package proj4;

public interface IUnMix {	
	   /** Us the parameter mixedMessage as the string point, that is, the mix message from the previous step.  The second parameter is the file where the list of undo commands is found. The returned string should be the original message. */
	   String UnMixUsingFile (String mixedMessage);
	}
