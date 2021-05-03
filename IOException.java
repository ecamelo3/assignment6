@SuppressWarnings("serial")
public class IOException extends Exception{
	public IOException(){
		super("Message");
	}
	
	public IOException(String message) {
		super(message);
	}

}
