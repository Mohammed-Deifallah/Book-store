package exceptions;

public class InvalidUserName extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public String getMessage(){
		return "Invalid username. Username must start with [a-zA-Z]";
	}

}
