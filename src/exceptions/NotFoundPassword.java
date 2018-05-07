package exceptions;

public class NotFoundPassword extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public String getMessage(){
		return "Password is wrong!";
	}

}
