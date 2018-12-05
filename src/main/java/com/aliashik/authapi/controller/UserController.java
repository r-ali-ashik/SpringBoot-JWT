package com.aliashik.authapi.controller;


import com.aliashik.authapi.entity.ApplicationUser;
import com.aliashik.authapi.repository.ApplicationUserRepository;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
	private ApplicationUserRepository applicationUserRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public UserController(ApplicationUserRepository applicationUserRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.applicationUserRepository = applicationUserRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@PostMapping("/sign-up")
	public ResponseEntity signUp(@RequestBody ApplicationUser user) {

		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		applicationUserRepository.save(user);

        JSONObject response = new JSONObject();
        response.put("status", "success");
        response.put("message", "sign up success");

        return ResponseEntity.status(HttpStatus.OK).body(response);
	}
}
