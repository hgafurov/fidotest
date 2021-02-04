package uz.hg.fidotest.rest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uz.hg.fidotest.dto.AuthenticationRequestDTO;
import uz.hg.fidotest.dto.CreateUserDto;
import uz.hg.fidotest.dto.TokenDto;
import uz.hg.fidotest.dto.UserDto;
import uz.hg.fidotest.model.User;
import uz.hg.fidotest.security.jwt.TokenProvider;
import uz.hg.fidotest.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
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
				Map<Object, Object> response = new HashMap<>();
				response.put("error", "Invalid username and password");
				response.put("msg", "Login yoki parol noto'gri.");
				return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
				//throw new UsernameNotFoundException("Login <" + login + "> nomli foydalanuvchi topilmadi");
			}
			String token = tokenProvider.createToken(login, user.getRoles());
			Map<Object, Object> response = new HashMap<>();
			//response.put("login", login);
			response.put("token", "Bearer "+token);
			return ResponseEntity.ok(response);
		} catch (AuthenticationException e) {
			Map<Object, Object> response = new HashMap<>();
			response.put("error", "Invalid username and password");
			response.put("msg", "Login yoki parol noto'gri.");
			return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
			//throw new BadCredentialsException("Invalid login or password");
		}
	}
	
	@PostMapping("create-user")
	public ResponseEntity<?> createUser(@RequestBody CreateUserDto newUser) {
		
		if (!newUser.getPassword1().equals(newUser.getPassword2())) {
			Map<Object, Object> response = new HashMap<>();
			response.put("error", "Invalid password");
			response.put("msg", "Пароль не совпадает. Введите точнее.");
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);		
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
	
	@PostMapping("guser")
	public ResponseEntity<?> getUser(@RequestBody TokenDto tokenDto) {
		System.out.println("Token = " + tokenDto.getToken());
		UserDto userDto = UserDto.fromUser(userService.findByLogin(tokenProvider.getLogin(tokenDto.getTokentFresh())));
		return new ResponseEntity<>(userDto, HttpStatus.OK);
	}
	
	@PostMapping("update-user")
	public ResponseEntity<?> updateUser(@RequestBody CreateUserDto updateUser) {
				
		User user = userService.findByLogin(updateUser.getLogin());
		if (user != null) {
			user.setFamiliya(updateUser.getFamiliya());
			user.setImya(updateUser.getImya());
			user.setEmail(updateUser.getEmail());
			UserDto userDto = UserDto.fromUser(userService.save(user));
			return new ResponseEntity<>(userDto, HttpStatus.OK);
		}
		Map<Object, Object> response = new HashMap<>();
		response.put("warn", "User saqlanmadi");
		response.put("msg", "Userni saqlash omadsiz yakunlandi");
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST );					
	}

}
