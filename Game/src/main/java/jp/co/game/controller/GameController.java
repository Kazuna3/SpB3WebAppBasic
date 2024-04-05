package jp.co.game.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import jp.co.game.bean.Result;
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
		BindingResult bindingResult,
		Model model
	) {

		if (bindingResult.hasErrors()) {

			// 単項目チェックでエラーを検出した場合の処理。
			System.out.println("単項目チェックでエラーを検出した。");
			return "gameScreen";

		}

		Integer ansOfPlayer = Integer.valueOf(answerForm.getAnswerNumber());

		Integer answerNumber = (Integer) session.getAttribute("answerNumber");

		answerForm.setResult(judge.kotaeAwase(answerNumber, ansOfPlayer));

		answerForm.setAnswerNumber("");

		List<Result> listResult = new ArrayList<>();

		listResult.add(new Result("1", "10", "hoge"));
		listResult.add(new Result("2", "20", "fuga"));
		listResult.add(new Result("3", "30", "pooo"));

		model.addAttribute("listResult", listResult);

		return "gameScreen";

	}

}
