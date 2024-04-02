package com.example.howToLinkData.form;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CheckBoxForm {

	@Pattern(regexp = "[0-9]", message = "単項目チェックでエラーを検出しました。１桁の数字以外のデータはエラーになります。")
	private String checkbox1;

	// フォームクラスのフィールドのデータ型は、String 型か、checkBox では、その配列が望ましいと考える。
	// 例えばフィールドのデータ型に Integer 型を採用した場合を考える。
	// ブラウザの開発ツールなどで、サーバー側に送信するデータが元々"1"となっていたものを、
	// "abc"に編集され、そのデータを送信されると、サーバー側が、そのデータを
	// フォームクラスのフィールドに設定する際、エラーが発生する。
	// そのエラーは、単項目チェックを設定していたとしても防げない。
	// その際発生したエラーメッセージは次の通り。
	// Failed to convert property value of type java.lang.String to required type java.lang.Integer for property checkbox1; For input string: "z"
	// ならばと、Integer 型のフィールドに、@Pattern アノテーションを付与して、正規表現で数字以外のデータの場合を、
	// 単項目チェックでデータ異常を検出できるようにしようとしたが、Integer 型のフィールドに、@Pattern アノテーションは、
	// そもそも設定できなかった。

	// @Pattern(regexp = "[0-9]", message = "単項目チェックでエラーを検出しました。１桁の数字以外のデータはエラーになります。")
	//	@Range(min = 1, max = 1, message = "単項目チェックでエラーを検出しました。１以外のデータはエラーになります。")
	//	private Integer checkbox1;

}
