package jp.co.game.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import jp.co.game.form.AnswerForm;
import jp.co.game.service.GenerateAnswer;
import jp.co.game.service.Judge;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class GameController {

	// 数当てゲームの答えの値。
	private final Judge judge;
	private final GenerateAnswer generateAnswer;
	private final HttpSession session;

	@GetMapping("/")
	public String initial(
		@ModelAttribute @Validated AnswerForm answerForm
	) {

		Integer answerNumber = generateAnswer.generate();
		System.out.println("■正解：" + answerNumber);
		session.setAttribute("answerNumber", answerNumber);
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

		Integer answerNumber = (Integer) session.getAttribute("answerNumber");

		answerForm.setResult(judge.kotaeAwase(answerNumber, ansOfPlayer));

		return "gameScreen";

	}

}
