package com.myproject.liketwitter;

import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class GreetingController {

	@GetMapping("/greeting")
	public String greeting(@RequestParam(name = "name", required = false, defaultValue = "Hi") String name, Map<String,Object> model) {
		model.put("name", name);

		return "greeting";
	}

	@GetMapping()
	public String main( Map<String,Object> model) {
		model.put("some", "Hello. its test");

		return "main";
	}

}
