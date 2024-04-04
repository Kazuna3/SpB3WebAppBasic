package jp.co.game.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jp.co.game.form.AnswerForm;

@Controller
public class GameController {

	// 数当てゲームの答えの値。
	private final Integer answerNumber = 123;

	@GetMapping("/")
	public String initial(
		@ModelAttribute @Validated AnswerForm answerForm
	) {

		return "gameScreen";

	}

	@PostMapping("/sendMyAnswer")
	public String judge(
		@ModelAttribute @Validated AnswerForm answerForm,
		BindingResult bindingResult
	) {

		if (bindingResult.hasErrors()) {

			// 単項目チェックでエラーを検出した場合の処理。
			System.out.println("単項目チェックでエラーを検出した。");
			return "gameScreen";

		}

		Integer ansOfPlayer = Integer.valueOf(answerForm.getAnswerNumber());

		if (answerNumber == ansOfPlayer) {

			// 正解した。
			System.out.println("正解した。");

		} else if (answerNumber < ansOfPlayer) {

			// プレイヤーの値が大きい。
			System.out.println("プレイヤーの値が大きい。");

		} else {

			// プレイヤーの値が小さい。
			System.out.println("プレイヤーの値が小さい。");

		}

		return "gameScreen";

	}

}
