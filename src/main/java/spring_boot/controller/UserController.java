package spring_boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import spring_boot.data_request.LoginUserReq;
import spring_boot.service.UserService;
import spring_boot.data_request.CreateUserReq;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/create")
	public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserReq req, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return ResponseEntity.created(null).body("error");
		}
		return userService.createUser(req);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody LoginUserReq req) {
		return userService.login(req);
	}
}
