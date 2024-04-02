package com.example.howToLinkData.form;

import lombok.Data;

@Data
public class InputOutputForm {

	private String textBox;
	private String passWord;
	private String radioBtn1;
	private String radioBtn2;
	private String checkBox1;

	// ２つ以上のチェックボックスを、グループ化して使用する場合は、
	// ［checkbox2］のように、そのデータ型は、String の配列が最善と考える。
	// 数値型にすると、サーバー内部の変換処理で、実行時エラーが発生する恐れがあるが、
	// String では、その心配が不要となる。
	// ブラウザと Web Server 間のデータの連携は、文字データとして、やり取りしている。
	// String の配列とする理由は、［elementPartsOfAForm.html］で
	// ［チェックボックス２］と［チェックボックス３］の動作を比べると理解できると思う。
	// 例えば、選択肢1,2,3を選ぶと、String の場合、連携されるデータは、［1,2,3］と言う文字列になる。
	// それを、画面側に戻すことになるが、Thymelef の選択肢の value に［1,2,3］が無いため、
	// 選択していた状態が、保てなくなるからである。
	// String の配列を使用した場合は、正しく連携できる。
	private String[] checkBox2;

	private String checkBox3;
	private String selectBox1;
	private String textArea1;

}
