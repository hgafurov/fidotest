package uz.hg.fidotest.service;

import java.util.List;

import uz.hg.fidotest.model.User;

public interface UserService {

	User register(User user);
	List<User> getAll();
	User findByLogin(String login);
	User findById(Long id);
	void delete(Long id);
	boolean checkLogin(String login);
}
