package com.myproject.liketwitter.controller;

import com.myproject.liketwitter.domain.Role;
import com.myproject.liketwitter.domain.User;
import com.myproject.liketwitter.domain.dto.UserDto;
import com.myproject.liketwitter.domain.utils.UserUtils;
import com.myproject.liketwitter.repos.UserRepos;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class UserController {


	private final UserRepos userRepos;

	public UserController(final UserRepos userRepos) {
		this.userRepos = userRepos;
	}


	@GetMapping
	public String userList (Model model) {
		model.addAttribute("users", userRepos.findAll());
		return "userList";
	}

	@GetMapping("{user}")
	public String userEditForm (@PathVariable User user, Model model) {
		model.addAttribute("user", user);
		model.addAttribute("roles", Role.values());
		return "userEdit";
	}


	@PostMapping
	public String userSave (
			@RequestParam String username,
			@RequestParam Map<String,String> form,
			@RequestParam("userId") UserDto userDto, Model model) {

		var user = UserUtils.getUserFromDto(userDto);

		user.setUsername(username);

		var roles = Arrays.stream(Role.values()).map(Role::name).collect(Collectors.toSet());

		user.getRoles().clear();

		form.keySet().forEach(k -> {
			if(roles.contains(k)) {
				user.getRoles().add(Role.valueOf(k));
			}
		});

		userRepos.save(user);

		model.addAttribute("user", user);
		model.addAttribute("roles", Role.values());
		return "redirect:/user";
	}



}
