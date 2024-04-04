package jp.co.game.service;

import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class GenerateAnswer {

	public Integer generate() {

		Random rnd = new Random();
		return rnd.nextInt(100) + 1;

	}

}
