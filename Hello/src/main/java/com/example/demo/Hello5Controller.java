package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Hello5Controller {

	// http://localhost:8080/hello5?name=James
	@GetMapping("/hello5")
	public ModelAndView hello5Handler(
		@RequestParam("name") String name,
		// @RequestParam String name,
		ModelAndView mv
	) {

		mv.addObject("name", name);
		mv.setViewName("hello55");

		return mv;

	}

}
