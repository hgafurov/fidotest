package uz.hg.fidotest.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import uz.hg.fidotest.model.Role;
import uz.hg.fidotest.model.Status;
import uz.hg.fidotest.model.User;

public final class FidoUserFactory {
	
	public FidoUserFactory() {
	}
	
	public static FidoUser create(User user) {
		return new FidoUser(
				user.getId(),
				user.getLogin(),
				user.getPassword(),
				user.getFamiliya(),
				user.getImya(),
				user.getEmail(),
				user.getStatus().equals(Status.ACTIVE),
				user.getUpdated(),
				mapToGrantedAuthority(user.getRoles())
		);	
	}
	
	private static List<GrantedAuthority> mapToGrantedAuthority(List<Role> userRoles) {		
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		userRoles.forEach(role -> {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		});
		
		return authorities;	
	}
}
