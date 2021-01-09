package uz.hg.fidotest.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import uz.hg.fidotest.model.User;
import uz.hg.fidotest.service.UserService;

@Service
public class FidoUserDetailsService implements UserDetailsService{

	private final UserService userService;
	
	@Autowired
	public FidoUserDetailsService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		User user = userService.findByLogin(login);
		
		FidoUser fidoUser = FidoUserFactory.create(user);
				
		return fidoUser;
	}

}
