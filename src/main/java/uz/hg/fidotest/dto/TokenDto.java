package uz.hg.fidotest.dto;

public class TokenDto {

	private String token;

	public TokenDto() {

	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	public String getTokentFresh() {
		return token.substring(7);
	}
	
}
