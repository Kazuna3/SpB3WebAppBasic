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

		// 回答履歴のデータをセッションから取得する。
		@SuppressWarnings("unchecked")
		List<Result> listResult = (List<Result>) session.getAttribute("listResult");

		if (null == listResult) {

			// 回答履歴のデータが未登録だった場合は、初期化処理を行う。
			listResult = new ArrayList<>();

		}

		if (bindingResult.hasErrors()) {

			// 単項目チェックでエラーを検出した場合の処理。
			System.out.println("単項目チェックでエラーを検出した。");

			if (0 < listResult.size()) {

				// モデルに回答履歴のデータを追加する。
				model.addAttribute("listResult", listResult);

			}

			return "gameScreen";

		}

		// 正解の値をセッションから取得する。
		Integer answerNumber = (Integer) session.getAttribute("answerNumber");

		// プレーヤが出した回答を取得する。
		Integer ansOfPlayer = Integer.valueOf(answerForm.getAnswerNumber());

		// 答え合わせを行う。
		String kotaeAwasenoKekka = judge.kotaeAwase(answerNumber, ansOfPlayer);

		// 答え合わせの結果を Form クラスに登録する。
		answerForm.setResult(kotaeAwasenoKekka);

		// 回答欄をブランクにする。
		answerForm.setAnswerNumber("");

		// 回答履歴のデータを生成する。
		listResult.add(new Result(listResult.size() + 1, String.valueOf(ansOfPlayer), kotaeAwasenoKekka));

		// 回答履歴のデータをセッションに保存する。
		session.setAttribute("listResult", listResult);

		// モデルに回答履歴のデータを追加する。
		model.addAttribute("listResult", listResult);

		return "gameScreen";

	}

}
