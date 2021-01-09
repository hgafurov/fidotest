package uz.hg.fidotest.dto;

import uz.hg.fidotest.model.User;

public class UserDto {
	
	private Long id;
	private String login;
	private String familiya;
	private String imya;
	private String email;
	
	public UserDto() {
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getFamiliya() {
		return familiya;
	}
	public void setFamiliya(String familiya) {
		this.familiya = familiya;
	}
	public String getImya() {
		return imya;
	}
	public void setImya(String imya) {
		this.imya = imya;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public User toUser() {
		User user = new User();
		user.setId(id);
		user.setLogin(login);
		user.setFamiliya(familiya);
		user.setImya(imya);
		user.setEmail(email);
		return user;
	}
	
	public static UserDto fromUser(User user) {
		UserDto userDto = new UserDto();
		userDto.setId(user.getId());
		userDto.setLogin(user.getLogin());
		userDto.setFamiliya(user.getFamiliya());
		userDto.setImya(user.getImya());
		userDto.setEmail(user.getEmail());
		return userDto;
	}
}
