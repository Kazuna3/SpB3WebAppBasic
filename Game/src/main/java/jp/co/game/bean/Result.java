package jp.co.game.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {

	// 結果の番号を保持する。
	private Integer seqNum;

	// ゲームのプレーヤの答えた数値を保持する。
	private String answerNumber;

	// 数当てゲームの答え合わせの評価結果文字列を保持する。
	private String result;

}
