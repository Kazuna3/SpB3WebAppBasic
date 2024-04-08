package jp.co.rdb.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jp.co.rdb.entity.Book;
import jp.co.rdb.repository.BookRepository;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BookController {

	private final BookRepository bookRepository;

	@GetMapping("/")
	public String book() {

		return "redirect:/h2-console";

	}

	@GetMapping("/hoge")
	public String hoge(
		Model model,
		@PageableDefault(page = 0, size = 5, sort = "id") Pageable pageable
	) {

		Page<Book> todoPage = bookRepository.findAll(pageable);
		List<Book> bookList = todoPage.getContent();

		// コンソールで確認する。
		bookList.forEach(
				bl -> System.out.println(
						"Id ［" + bl.getId() + "］" + "Title［" + bl.getTitle() + "］"));

		return "redirect:/h2-console";

	}

}
