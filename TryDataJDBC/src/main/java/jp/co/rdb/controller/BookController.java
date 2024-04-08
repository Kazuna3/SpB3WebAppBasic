package jp.co.rdb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookController {

	@GetMapping("/")
	public String book() {

		return "redirect:/h2-console";

	}

}
