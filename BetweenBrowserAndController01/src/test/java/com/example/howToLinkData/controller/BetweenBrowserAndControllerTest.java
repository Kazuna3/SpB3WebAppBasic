// Controller のテストで参考にしたサイト
// https://qiita.com/ryo2132/items/ec10116238e1e1f333a1
// https://www.tsuchiya.blog/spring-boot-step8/
// https://qiita.com/aoyaman/items/2f4cbda117f6766188f0

package com.example.howToLinkData.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.howToLinkData.form.CheckBoxForm;
import com.example.howToLinkData.form.ExOfBtnImplForm;
import com.example.howToLinkData.form.InputOutputForm;

@SpringBootTest
public class BetweenBrowserAndControllerTest {

	private MockMvc mockMvc;

	@Autowired
	WebApplicationContext webApplicationContext;

	@BeforeEach
	void setup() {

		// @AutoConfigureMockMvcというアノテーションを使うとこの初期化は不要だが、
		// 問題が起きることもあるので手動で初期化している。
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

	}

	@Test
	@DisplayName("Get Method で \"/\" にアクセスした場合のテスト")
	void defaultHandler1Test() throws Exception {

		MvcResult mvcResult = mockMvc
				// Get Method で "/" にアクセスした場合のテストを行う。
				.perform(get("/"))
				// modelに inputOutputForm という名前でオブジェクトが格納されていることを確認する。
				.andExpect(model().attributeExists("inputOutputForm"))
				.andExpect(status().isOk())
				.andExpect(view().name("elementPartsOfAForm"))
				.andReturn();

		// Model の値が、想定通りかテストする。
		InputOutputForm inputOutputForm = (InputOutputForm) mvcResult.getModelAndView().getModel()
				.get("inputOutputForm");

		assertEquals(inputOutputForm.getCheckBox1(), null);
		assertEquals(inputOutputForm.getCheckBox3(), null);
		assertEquals(inputOutputForm.getPassWord(), "パスワードの初期値");
		assertEquals(inputOutputForm.getRadioBtn1(), "1");
		assertEquals(inputOutputForm.getRadioBtn2(), null);
		assertEquals(inputOutputForm.getSelectBox1(), "1");
		assertEquals(inputOutputForm.getTextArea1(), "テキストエリアの初期値");
		assertEquals(inputOutputForm.getTextBox(), "テキストボックスの初期値");

		// 次行ではテストできない。String 配列の値は、ループで確認するしかないみたい。
		// assertEquals(inputOutputForm.getCheckBox2(), new String[] { "1" });
		String[] strArray1 = inputOutputForm.getCheckBox2();
		String[] strArray2 = { "1" };

		assertEquals(strArray1.length, strArray2.length);

		for (int i = 0; i < strArray1.length; i++) {

			assertEquals(strArray1[i], strArray2[i]);
			System.out.println(strArray1[i] + " " + strArray2[i]);

		}

	}

	@Test
	@DisplayName("Post Method で \"/firstHandler\" にアクセスした場合のテスト")
	void firstHandlerTest() throws Exception {

		mockMvc
				// Post Method で "/firstHandler" にアクセスした場合のテストを行う。
				.perform(post("/firstHandler"))
				// modelに inputOutputForm という名前でオブジェクトが格納されていることを確認する。
				.andExpect(model().attributeExists("inputOutputForm"))
				.andExpect(status().isOk())
				.andExpect(view().name("elementPartsOfAForm"))
				.andReturn();

	}

	@Test
	@DisplayName("Get Method で \"/onlyOneCheckBox\" にアクセスした場合のテスト")
	void onlyOneCheckBoxGetHandlerTest() throws Exception {

		mockMvc
				// Get Method で "/onlyOneCheckBox" にアクセスした場合のテストを行う。
				.perform(get("/onlyOneCheckBox"))
				// modelに checkBoxForm という名前でオブジェクトが格納されていることを確認する。
				.andExpect(model().attributeExists("checkBoxForm"))
				.andExpect(status().isOk())
				.andExpect(view().name("onlyOneCheckBox"))
				.andReturn();

	}

	@Test
	@DisplayName("Post Method で \"/onlyOneCheckBox\" にアクセスした場合のテスト")
	void onlyOneCheckBoxPostHandlerTest() throws Exception {

		mockMvc
				// Post Method で "/onlyOneCheckBox" にアクセスした場合のテストを行う。
				.perform(post("/onlyOneCheckBox"))
				// modelに checkBoxForm という名前でオブジェクトが格納されていることを確認する。
				.andExpect(model().attributeExists("checkBoxForm"))
				.andExpect(status().isOk())
				.andExpect(view().name("onlyOneCheckBox"))
				.andReturn();

		CheckBoxForm checkBoxForm = new CheckBoxForm();
		checkBoxForm.setCheckBox1("z");

		mockMvc
				// Post Method で "/onlyOneCheckBox" にアクセスした場合のテストを行う。
				// .perform(post("/onlyOneCheckBox").param("checkBoxForm", checkBoxForm))
				.perform(post("/onlyOneCheckBox").flashAttr("checkBoxForm", checkBoxForm))
				// modelに checkBoxForm という名前でオブジェクトが格納されていることを確認する。
				.andExpect(model().attributeExists("checkBoxForm"))
				.andExpect(status().isOk())
				.andExpect(view().name("onlyOneCheckBox"))
				.andReturn();

	}

	@Test
	@DisplayName("Get Method で \"/exampleOfButtonImplementation_1\" にアクセスした場合のテスト")
	void buttonAndHiddenGetHandler1Test() throws Exception {

		MvcResult mvcResult = mockMvc
				// Get Method で "/exampleOfButtonImplementation_1" にアクセスした場合のテストを行う。
				.perform(get("/exampleOfButtonImplementation_1"))
				// modelに exOfBtnImplForm という名前でオブジェクトが格納されていることを確認する。
				.andExpect(model().attributeExists("exOfBtnImplForm"))
				.andExpect(status().isOk())
				.andExpect(view().name("buttonAndHidden"))
				.andReturn();

		// Model の値が、想定通りかテストする。
		ExOfBtnImplForm exOfBtnImplForm = (ExOfBtnImplForm) mvcResult.getModelAndView().getModel()
				.get("exOfBtnImplForm");

		assertEquals(exOfBtnImplForm.getHidden_1(), "ヒドゥン");

	}

	@Test
	@DisplayName("Post Method で \"/exampleOfButtonImplementation_1\" にアクセスした場合のテスト")
	void buttonAndHiddenPostHandler1Test() throws Exception {

		ExOfBtnImplForm exOfBtnImplForm_1 = new ExOfBtnImplForm();
		exOfBtnImplForm_1.setHidden_1("ヒドゥン");

		MvcResult mvcResult = mockMvc
				// Post Method で "/exampleOfButtonImplementation_1" にアクセスした場合のテストを行う。
				.perform(post("/exampleOfButtonImplementation_1").flashAttr("exOfBtnImplForm", exOfBtnImplForm_1))
				// modelに exOfBtnImplForm という名前でオブジェクトが格納されていることを確認する。
				.andExpect(model().attributeExists("exOfBtnImplForm"))
				.andExpect(status().isOk())
				.andExpect(view().name("buttonAndHidden"))
				.andReturn();

		// Model の値が、想定通りかテストする。
		ExOfBtnImplForm exOfBtnImplForm_2 = (ExOfBtnImplForm) mvcResult.getModelAndView().getModel()
				.get("exOfBtnImplForm");

		System.out.println("■1 :=>" + exOfBtnImplForm_2.getHidden_1());
		System.out.println("■2 :=>" + exOfBtnImplForm_1.getHidden_1());

		assertEquals(exOfBtnImplForm_2.getHidden_1(), exOfBtnImplForm_1.getHidden_1());

	}

	@Test
	@DisplayName("Post Method で \"/exampleOfButtonImplementation_2\" にアクセスした場合のテスト")
	void buttonAndHiddenPostHandler2Test() throws Exception {

		mockMvc
				// Post Method で "/exampleOfButtonImplementation_2" にアクセスした場合のテストを行う。
				.perform(post("/exampleOfButtonImplementation_2"))
				// modelに exOfBtnImplForm という名前でオブジェクトが格納されていることを確認する。
				.andExpect(model().attributeExists("exOfBtnImplForm"))
				.andExpect(status().isOk())
				.andExpect(view().name("buttonAndHidden"))
				.andReturn();

	}

}
