package jp.co.rdb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class H2Controller {

	@GetMapping("/")
	public String handlerMethod() {

		// return "redirect:/h2-console";
		return "redirect:/list";

	}

}
