package com.myproject.liketwitter.controller;

import com.myproject.liketwitter.domain.Message;
import com.myproject.liketwitter.domain.User;
import com.myproject.liketwitter.domain.dto.UserDto;
import com.myproject.liketwitter.domain.utils.UserUtils;
import com.myproject.liketwitter.repos.MessageRepos;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

	private static final String MESSAGE_VARIABLE = "messages";

	private final MessageRepos messageRepos;

	public MainController(final MessageRepos messageRepos) {
		this.messageRepos = messageRepos;
	}

	@GetMapping("/")
	public String greeting(Map<String, Object> model) {

		return "greeting";
	}

	@GetMapping("/main")
	public String main(@RequestParam(required = false, defaultValue = StringUtils.EMPTY) String filter, Model model) {

		Iterable<Message> all ;

		if (filter != null && !filter.isEmpty()) {
			all = messageRepos.findByTag(filter);
		} else {
			all = messageRepos.findAll();
		}

		model.addAttribute(MESSAGE_VARIABLE, all);
		model.addAttribute("filter", filter);

		return "main";
	}

	@GetMapping("/login")
	public String login(Map<String, Object> model) {

		return "login";
	}

	@PostMapping("/main")
	public String add(
			@AuthenticationPrincipal UserDto userDto,
			@RequestParam String text,
			@RequestParam String tag, Model model
	) {
		var user = UserUtils.getUserFromDto(userDto);
		var message = Message.init().setText(text).setTag(tag).setAuthor(user).build();

		messageRepos.save(message);

		var all = messageRepos.findAll();
		model.addAttribute(MESSAGE_VARIABLE, all);

		return "main";
	}

	@PutMapping
	public String remove(@RequestParam Long id, Model model) {
		var messageOptional = messageRepos.findById(id);

		Message message;

		message = messageOptional.orElseGet(() -> Message.init().build());

		messageRepos.delete(message);

		var all = messageRepos.findAll();
		model.addAttribute(MESSAGE_VARIABLE, all);

		return "main";
	}
}
