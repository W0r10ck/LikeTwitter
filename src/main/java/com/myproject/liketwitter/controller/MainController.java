package com.myproject.liketwitter.controller;

import static java.util.UUID.randomUUID;
import com.myproject.liketwitter.domain.Message;
import com.myproject.liketwitter.domain.User;
import com.myproject.liketwitter.repos.MessageRepos;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class MainController {

	private static final String MESSAGE_VARIABLE = "messages";

	private final MessageRepos messageRepos;

	public MainController(final MessageRepos messageRepos) {
		this.messageRepos = messageRepos;
	}

	@Value("${upload.path}")
	private String uploadPath;

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
			@AuthenticationPrincipal User user,
			@RequestParam("file") MultipartFile file,
			@RequestParam String text,
			@RequestParam String tag, Model model
	) throws IOException {
		var message = Message.init().setText(text).setTag(tag).setAuthor(user).build();

		if (file != null && !file.getOriginalFilename().isEmpty()) {

			var newFile = new File(uploadPath);
			if(!newFile.exists()){
				newFile.mkdir();
			}

			String uuuidFile = randomUUID().toString();

			var resultFileName = uuuidFile + "." + file.getOriginalFilename();

			file.transferTo(new File(uploadPath + "/" +  resultFileName));

			message.setFilename(resultFileName);
		}

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
