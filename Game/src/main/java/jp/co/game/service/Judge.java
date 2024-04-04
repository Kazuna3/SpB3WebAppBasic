package jp.co.game.service;

import org.springframework.stereotype.Service;

@Service
public class Judge {

	public String kotaeAwase(Integer seikai, Integer kaitou) {

		String result = "";

		if (seikai == kaitou) {

			// 正解した。
			System.out.println("正解した。");
			result = "正解した。";

		} else if (seikai < kaitou) {

			// プレイヤーの値が大きい。
			System.out.println("プレイヤーの値が大きい。");
			result = "プレイヤーの値が大きい。";

		} else {

			// プレイヤーの値が小さい。
			System.out.println("プレイヤーの値が小さい。");
			result = "プレイヤーの値が小さい。";

		}

		return result;

	}

}
