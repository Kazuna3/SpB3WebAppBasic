package com.example.howToLinkData;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.howToLinkData.form.CheckBoxForm;
import com.example.howToLinkData.form.InputOutputForm;

@Controller
public class BetweenBrowserAndController {

	// http://localhost:8080
	@GetMapping("/")
	public String defaultHandler1(
		@ModelAttribute InputOutputForm inOutF
	) {

		// 初期値を設定する。
		inOutF.setTextBox("初期値");

		// 初期値を設定する。
		// パスワードには、何も値を設定できないみたい・・・
		inOutF.setPassWord("初期値");

		// ラジオボタンの初期選択を設定する。
		inOutF.setRadioBtn1("1");

		// チェックボックスの初期選択を設定する。
		// String[] strArray = { "1" };
		// inOutF.setCheckbox2(strArray);
		// 次行は、上２行と同じ処理である。
		inOutF.setCheckbox2(new String[] { "1" });

		// セレクトボックスの初期選択を設定する。
		inOutF.setSelectBox1("1");

		// テキストエリアの初期値を設定する。
		// inOutF.setTextArea1("テキストエリアの初期値");

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

	// http://localhost:8080/onlyOneCheckbox
	@GetMapping("/onlyOneCheckbox")
	public String onlyOneCheckboxGetHandler(
		@ModelAttribute CheckBoxForm cBForm
	) {

		// 初期画面表示でチェックボックスにチェックを入れる場合は、
		// 次行をコメントインする事。
		cBForm.setCheckbox1("1");

		return "onlyOneCheckbox";

	}

	@PostMapping("/onlyOneCheckbox")
	public String onlyOneCheckboxPostHandler(
		@ModelAttribute CheckBoxForm cBForm
	) {

		System.out.println(cBForm);

		return "onlyOneCheckbox";

	}

	// http://localhost:8080/onlyOneCheckbox
	@GetMapping("/buttonAndHidden")
	public String buttonAndHiddenGetHandler(
		@ModelAttribute CheckBoxForm cBForm
	) {

		// 初期画面表示でチェックボックスにチェックを入れる場合は、
		// 次行をコメントインする事。
		cBForm.setCheckbox1("1");

		return "onlyOneCheckbox";

	}

	@PostMapping("/buttonAndHidden")
	public String buttonAndHiddenPostHandler(
		@ModelAttribute CheckBoxForm cBForm
	) {

		System.out.println(cBForm);

		return "onlyOneCheckbox";

	}

	// TODO checkbox を checkBox に変更する？

}
