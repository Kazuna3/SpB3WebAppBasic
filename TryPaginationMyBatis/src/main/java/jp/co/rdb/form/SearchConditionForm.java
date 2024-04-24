package jp.co.rdb.form;

import lombok.Data;

@Data
public class SearchConditionForm {

	private String shimei;

	public SearchConditionForm() {

		shimei = "";

	}

}
