package uz.hg.fidotest.dto;

public class AuthenticationRequestDTO {
	
	private String login;
	private String password;
	
	public AuthenticationRequestDTO() {
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
