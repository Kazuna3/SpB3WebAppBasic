package jp.co.rdb.form;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class PersonForm {

	@NotEmpty(message = "入力値がNullまたは空文字列だった為、エラーです。")
	private String shimei;

}
