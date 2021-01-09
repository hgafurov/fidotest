package uz.hg.fidotest.rest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uz.hg.fidotest.dto.AuthenticationRequestDTO;
import uz.hg.fidotest.dto.CreateUserDto;
import uz.hg.fidotest.dto.UserDto;
import uz.hg.fidotest.model.User;
import uz.hg.fidotest.security.jwt.TokenProvider;
import uz.hg.fidotest.service.UserService;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthenticationRestController {
	
	private final AuthenticationManager authenticationManager;
	private final TokenProvider tokenProvider;
	private final UserService userService;
	
	@Autowired
	public AuthenticationRestController(AuthenticationManager authenticationManager, TokenProvider tokenProvider,
			UserService userService) {
		this.authenticationManager = authenticationManager;
		this.tokenProvider = tokenProvider;
		this.userService = userService;
	}
	
	@PostMapping("login")
	public ResponseEntity<?> login(@RequestBody AuthenticationRequestDTO requestDto) {
		try {
			String login = requestDto.getLogin();
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					login, requestDto.getPassword()));
			User user = userService.findByLogin(login);
			if (user == null) {
				throw new UsernameNotFoundException("Login <" + login + "> nomli foydalanuvchi topilmadi");
			}
			String token = tokenProvider.createToken(login, user.getRoles());
			Map<Object, Object> response = new HashMap<>();
			response.put("login", login);
			response.put("token", token);
			return ResponseEntity.ok(response);
		} catch (AuthenticationException e) {
			throw new BadCredentialsException("Invalid login or password");
		}
	}
	
	@PostMapping("create-user")
	public ResponseEntity<?> createUser(@RequestBody CreateUserDto newUser) {
		
		if (!newUser.getPassword1().equals(newUser.getPassword2())) {
			Map<Object, Object> response = new HashMap<>();
			response.put("error", "Invalid password");
			response.put("msg", "Пароль не совпадает. Введите точнее.");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);		
		}
		
		if (userService.checkLogin(newUser.getLogin())) {
			Map<Object, Object> response = new HashMap<>();
			response.put("error", "Invalid Login");
			response.put("msg", "Пользователь с таким логином уже существует. Придумайте другой логин.");
			return new ResponseEntity<>(response, HttpStatus.CONFLICT );			
		}
		
		User user = newUser.toUser();
		user.setPassword(newUser.getPassword1());			
		UserDto userDto = UserDto.fromUser(userService.register(user));
		
		return new ResponseEntity<>(userDto, HttpStatus.OK);
	}
	
	
	

}
