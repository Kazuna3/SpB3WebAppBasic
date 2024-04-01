package com.example.howToLinkData;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.howToLinkData.form.InputOutputForm;

@Controller
public class BetweenBrowserAndController {

	// http://localhost:8080
	@GetMapping("/")
	public String defaultHandler(
		// @ModelAttribute InputOutputForm inputOutputForm
		@ModelAttribute InputOutputForm inOutF
	) {

		inOutF.setTextBox("初期値");

		return "elementPartsOfAForm";

	}

	// http://localhost:8080/firstHandler
	@PostMapping("/firstHandler")
	public String firstHandler(
		@ModelAttribute InputOutputForm inOutF
	) {

		System.out.println(inOutF);

		inOutF.setTextBox("変えちゃう");

		return "elementPartsOfAForm";

	}

}
