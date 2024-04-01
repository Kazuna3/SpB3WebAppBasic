package com.example.howToLinkData;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.howToLinkData.form.InputOutputForm;

@Controller
public class BetweenBrowserAndController {

	// http://localhost:8080
	@GetMapping("/")
	public String defaultHandler1(
		@ModelAttribute InputOutputForm inOutF
	) {

		inOutF.setTextBox("初期値");

		// パスワードには、何も値を設定できないみたい・・・
		inOutF.setPassWord("初期値");

		// html の radio の checked 属性が、無視される為、
		// ラジオボタンの初期選択を設定する。
		inOutF.setRadioBtn1("1");

		// チェックボックスの初期選択を設定する。
		// String[] strArray = { "1" };
		// inOutF.setCheckbox2(strArray);
		// 次行は、上２行と同じ処理である。
		inOutF.setCheckbox2(new String[] { "1" });

		return "elementPartsOfAForm";

	}

	// 
	/**
	 * defaultHandler1 メソッドの書き換え版。
	 * 本メソッドの起動用 URL => http://localhost:8080
	 * @param model
	 * @return String ビューネームの文字列
	 */
	// @GetMapping("/")
	public String defaultHandler2(
		Model model
	) {

		InputOutputForm inOutF = new InputOutputForm();
		inOutF.setTextBox("初期値");
		model.addAttribute("inputOutputForm", inOutF);

		return "elementPartsOfAForm";

	}

	// http://localhost:8080/firstHandler
	@PostMapping("/firstHandler")
	public String firstHandler(
		@ModelAttribute InputOutputForm inOutF
	) {

		System.out.println(inOutF);

		// inOutF.setTextBox("変えちゃう");
		// パスワードには、何も値を設定できないみたい・・・
		// inOutF.setPassWord("変えちゃう");

		return "elementPartsOfAForm";

	}

}
