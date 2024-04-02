package com.example.howToLinkData.form;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CheckBoxForm {

	@Pattern(regexp = "[0-9]", message = "単項目チェックでエラーを検出しました。１桁の数字以外のデータはエラーになります。")
	private String checkbox1;

	/*
	フォームクラスのフィールドのデータ型は、String 型か、複数の checkBox を
	グループ化して使用する場合は、その配列が望ましいと考える。
	
	例えば、フィールドのデータ型に、Integer 型を採用した場合を考える。
	
	①元々"1"となっていた value 属性の値を、ブラウザの開発ツールなどで、"abc"
	　に編集する。
	②サーバー側に、そのデータを送信する。
	③サーバー側が、そのデータを、フォームクラスのフィールドに設定しようとする。
	　しかし、型変換に失敗してエラーが発生する。
	
	①～③の操作を行い発生したエラーメッセージは、次の通り。
	Failed to convert property value of type java.lang.String to required
	type java.lang.Integer for property checkbox1; For input string: "abc"
	
	因みに、正規表現で数字以外のデータの場合を、単項目チェック エラーに出来
	ればと考え試した。が、Integer 型のフィールドに、@Pattern アノテーション
	を設定して実行すると、実行時エラーが発生したので、その試みは失敗に終わっ
	た。
	
	String 型の場合、上述の不具合は起こらない。
	*/

	// @Pattern(regexp = "[0-9]", message = "単項目チェックでエラーを検出しました。１桁の数字以外のデータはエラーになります。")
	//	@Range(min = 1, max = 1, message = "単項目チェックでエラーを検出しました。１以外のデータはエラーになります。")
	//	private Integer checkbox1;

}
