package uz.hg.fidotest.security;

import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class FidoUser implements UserDetails {
	
	private static final long serialVersionUID = 202101070417L;
	
	private final Long id;
	private final String login;
	private final String password;
	private final String familiya;
	private final String imya;
	private final String email;
	private final boolean enabled;
	private final Date lastPasswordResetDate; 
	private final Collection<? extends GrantedAuthority> authorities;
		
	public FidoUser(
			Long id, 
			String login, 
			String password, 
			String familiya, 
			String imya, 
			String email, 
			boolean enabled,
			Date lastPasswordResetDate,
			Collection<? extends GrantedAuthority> authorities
	) {
		this.id = id;
		this.login = login;
		this.password = password;
		this.familiya = familiya;
		this.imya = imya;
		this.email = email;
		this.enabled = enabled;
		this.lastPasswordResetDate = lastPasswordResetDate;
		this.authorities = authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return login;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	public Long getId() {
		return id;
	}

	public String getFamiliya() {
		return familiya;
	}

	public String getImya() {
		return imya;
	}

	public String getEmail() {
		return email;
	}

	public Date getLastPasswordResetDate() {
		return lastPasswordResetDate;
	}

}
