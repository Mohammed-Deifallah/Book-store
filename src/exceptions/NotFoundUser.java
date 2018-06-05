package exceptions;

public class NotFoundUser extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public String getMessage(){
		return ("Username is not found!");
	}

}
