package exceptions;

public class NotFoundEmail extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage(){
		return "That email is not found!";
	}
}
