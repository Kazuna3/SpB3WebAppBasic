package jp.co.game.form;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AnswerForm {

	// @formatter:off
	@Pattern(
		regexp = "\\d{1,3}?",
		message = "単項目チェックでエラーを検出しました。"
		        + "１～３桁の数字以外のデータはエラーになります。"
	)
	// @formatter:on 
	private String answerNumber;

}
