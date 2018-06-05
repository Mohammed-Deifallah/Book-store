package exceptions;

public class NotMatchingPasswords extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public String getMessage(){
		return "Passwords not matching";
	}
}
