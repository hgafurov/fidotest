package uz.hg.fidotest.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import uz.hg.fidotest.model.Role;
import uz.hg.fidotest.model.Status;
import uz.hg.fidotest.model.User;
import uz.hg.fidotest.repository.RoleRepository;
import uz.hg.fidotest.repository.UserRepository;
import uz.hg.fidotest.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	private final BCryptPasswordEncoder passwordEncoder;
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	
	
	@Autowired
	public UserServiceImpl(BCryptPasswordEncoder passwordEncoder, UserRepository userRepository,
			RoleRepository roleRepository) {
		this.passwordEncoder = passwordEncoder;
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}

	@Override
	public User register(User user) {
		Role role = roleRepository.findByName("ROLE_USER"); 
		List<Role> userRoles = new ArrayList<>();
		userRoles.add(role);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRoles(userRoles);
		user.setStatus(Status.ACTIVE);
		
		User registeredUser = userRepository.save(user);
		
		return registeredUser;
	}

	@Override
	public List<User> getAll() {
		List<User> users = userRepository.findAll();
		return users;
	}

	@Override
	public User findByLogin(String login) {
		User user = userRepository.findByLogin(login);
		return user;
	}

	@Override
	public User findById(Long id) {
		User user = userRepository.findById(id).orElse(null);
		return user;
	}

	@Override
	public void delete(Long id) {
		userRepository.deleteById(id);	
	}

	@Override
	public boolean checkLogin(String login) {
		return (1 == userRepository.countLogin(login));
	}

	
}
