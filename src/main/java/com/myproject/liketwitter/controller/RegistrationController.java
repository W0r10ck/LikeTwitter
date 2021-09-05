package com.myproject.liketwitter.controller;

import com.myproject.liketwitter.domain.Role;
import com.myproject.liketwitter.domain.User;
import com.myproject.liketwitter.domain.dto.UserDto;
import com.myproject.liketwitter.repos.UserRepos;
import java.util.Collections;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {


	private final UserRepos userRepos;

	public RegistrationController(final UserRepos userRepos) {
		this.userRepos = userRepos;
	}

	@GetMapping("/registration")
	public String registration() {

		return "registration";
	}


	@PostMapping("/registration")
	public String addUser(UserDto userDto, Model model) {

		User userFromDb = userRepos.findByUsername(userDto.getUsername());

		if (userFromDb != null) {
			model.addAttribute("message", "User exists!");
			return "registration";
		}

		var newUser = User.init().setActive(true).setPassword(userDto.getPassword()).setUsername(userDto.getUsername())
				.setRoles(Collections.singleton(Role.USER)).build();
		userRepos.save(newUser);

		return "redirect:/login";
	}
}
