package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// @RestControllerアノテーション「コントローラークラスのメソッドで処理した結果を、
// そのままレスポンスとしてブラウザへ送信する」ことを表すアノテーションです。
// 本来はJSONやXMLなどを返す「RESTインターフェース」で使うものですが、
// 「テキストを返す」機能を流用できるので、このアノテーションを利用します
@RestController
public class Hello1_4Controller {

	// http://localhost:8080
	// http://localhost:8080/
	@GetMapping("/")
	public String getDefaultHandler() {

		return "Hello1Controller クラスが応答しました。";

	}

	// http://localhost:8080/hello1
	@GetMapping("/hello1")
	public String hello1Handler() {

		return "hello1";

	}

	// http://localhost:8080/hello2?name=James
	@GetMapping("/hello2")
	public String hello2Handler(
		// @RequestParam("name") String name
		@RequestParam String name
	) {

		return "Hello,world!こんにちは" + name;

	}

	// http://localhost:8080/hello3/James
	@GetMapping("/hello3/{name}")
	public String hello3Handler(
		// @PathVariable("name") String name
		@PathVariable String name
	) {

		return "Hello,world!" + "こんにちは「" + name + "」さん！";

	}

	// http://localhost:8080/hello3/James/26
	@GetMapping("/hello3/{name}/{age}")
	public String hello3Handler(
		@PathVariable String name,
		@PathVariable String age
	) {

		return "Hello,world!" + "こんにちは「" + name + "」さん！" + "年齢は" + age + "歳ですね。";

	}

	// http://localhost:8080/hello44.html
	@PostMapping("/hello4")
	public String hello4Handler(
		// @RequestParam("simei") String simei
		@RequestParam String simei
	) {

		return "Hello,world!" + "こんにちは「" + simei + "」さん！";

	}

}
