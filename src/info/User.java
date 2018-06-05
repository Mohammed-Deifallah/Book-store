package info;

public class User {
	private String username, email, password;
	
	public User(String username, String email, String password){
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	public String getEmail(){
		return email;
	}
	
	public String getPassword(){
		return password;
	}
	
	public String getUserName(){
		return username;
	}
}
