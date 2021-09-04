package com.myproject.liketwitter.controller;

import com.myproject.liketwitter.domain.Message;
import com.myproject.liketwitter.repos.MessageRepos;
import java.util.Map;
import org.springframework.stereotype.Controller;
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
	public String main(Map<String, Object> model) {

		var all = messageRepos.findAll();
		model.put(MESSAGE_VARIABLE, all);

		return "main";
	}

	@GetMapping("/login")
	public String login(Map<String, Object> model) {

		return "login";
	}

	@PostMapping("/main")
	public String add(@RequestParam String text, @RequestParam String tag, Map<String, Object> model) {
		var message = Message.init().setText(text).setTag(tag).build();

		messageRepos.save(message);

		var all = messageRepos.findAll();
		model.put(MESSAGE_VARIABLE, all);

		return "main";
	}

	@PostMapping("/filter")
	public String filter(@RequestParam String filter, Map<String, Object> model) {

		Iterable<Message> all;

		if (filter != null && !filter.isEmpty()) {
			all = messageRepos.findByTag(filter);
		} else {
			all = messageRepos.findAll();
		}

		model.put(MESSAGE_VARIABLE, all);

		return "main";
	}

	@PutMapping
	public String remove(@RequestParam Long id, Map<String, Object> model) {
		var messageOptional = messageRepos.findById(id);

		Message message;

		message = messageOptional.orElseGet(() -> Message.init().build());

		messageRepos.delete(message);

		var all = messageRepos.findAll();
		model.put(MESSAGE_VARIABLE, all);

		return "main";
	}
}
