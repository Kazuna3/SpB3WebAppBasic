package com.example.howToLinkData.controller;

import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.howToLinkData.form.CheckBoxForm;
import com.example.howToLinkData.form.ExOfBtnImplForm;
import com.example.howToLinkData.form.InputOutputForm;

@Controller
public class BetweenBrowserAndController {

	// http://localhost:8080
	@GetMapping("/")
	public String defaultHandler1(
		@ModelAttribute InputOutputForm inOutF
	) {

		// 初期値を設定する。
		inOutF.setTextBox("テキストボックスの初期値");

		// 初期値を設定する。
		// パスワードには、何も値を設定できないみたい・・・
		inOutF.setPassWord("パスワードの初期値");

		// ラジオボタンの初期選択を設定する。
		inOutF.setRadioBtn1("1");

		// チェックボックスの初期選択を設定する。
		// String[] strArray = { "1" };
		// inOutF.setCheckBox2(strArray);
		// 次行は、上２行と同じ処理である。
		inOutF.setCheckBox2(new String[] { "1" });

		// セレクトボックスの初期選択を設定する。
		inOutF.setSelectBox1("1");

		// テキストエリアの初期値を設定する。
		inOutF.setTextArea1("テキストエリアの初期値");

		return "elementPartsOfAForm";

	}

	// 
	/**
	 * defaultHandler1 メソッドで @ModelAttribute を使わない場合の書き換え版。
	 * 本メソッドの起動用 URL => http://localhost:8080
	 * @param model
	 * @return String ビューネームの文字列
	 */
	/*
	@GetMapping("/")
	public String defaultHandler1(
		Model model
	) {
	
		InputOutputForm inOutF = new InputOutputForm();
		inOutF.setTextBox("初期値");
		model.addAttribute("inputOutputForm", inOutF);
	
		return "elementPartsOfAForm";
	
	}
	*/

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

	// http://localhost:8080/onlyOneCheckBox
	@GetMapping("/onlyOneCheckBox")
	public String onlyOneCheckBoxGetHandler(
		@ModelAttribute CheckBoxForm cBForm
	) {

		// 初期画面表示でチェックボックスにチェックを入れる場合は、
		// 次行をコメントインする事。
		cBForm.setCheckBox1("1");
		// cBForm.setCheckBox1(1);

		return "onlyOneCheckBox";

	}

	@PostMapping("/onlyOneCheckBox")
	public String onlyOneCheckBoxPostHandler(
		@ModelAttribute @Validated CheckBoxForm cBForm,
		BindingResult bindingResult
	) {

		System.out.println(cBForm);

		if (bindingResult.hasFieldErrors()) {

			System.out.println("単項目チェックでエラーを検出しました！");

		} else {

			System.out.println("単項目チェックをパスしました。");

		}

		return "onlyOneCheckBox";

	}

	// http://localhost:8080/exampleOfButtonImplementation_1
	@GetMapping("/exampleOfButtonImplementation_1")
	public String buttonAndHiddenGetHandler1(
		@ModelAttribute ExOfBtnImplForm exOfBtnImplForm
	) {

		System.out.println("ハンドラメソッド：buttonAndHiddenGetHandler1 が実行されます。");

		// 初期値を設定する。
		exOfBtnImplForm.setHidden_1("ヒドゥン");
		System.out.println(exOfBtnImplForm);

		return "buttonAndHidden";

	}

	@PostMapping("/exampleOfButtonImplementation_1")
	public String buttonAndHiddenPostHandler1(
		@ModelAttribute ExOfBtnImplForm exOfBtnImplForm
	) {

		System.out.println("ハンドラメソッド：buttonAndHiddenPostHandler1 が実行されます。");
		System.out.println(exOfBtnImplForm);

		exOfBtnImplForm.setHidden_1(exOfBtnImplForm.getHidden_1() + " " + exOfBtnImplForm.getHidden_1());
		System.out.println(exOfBtnImplForm);

		return "buttonAndHidden";

	}

	@PostMapping("/exampleOfButtonImplementation_2")
	public String buttonAndHiddenPostHandler2(
		@ModelAttribute ExOfBtnImplForm exOfBtnImplForm
	) {

		System.out.println("ハンドラメソッド：buttonAndHiddenPostHandler2 が実行されます。");
		System.out.println(exOfBtnImplForm);

		return "buttonAndHidden";

	}

}
