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

	//	@GetMapping("/")
	//	public String book() {
	//
	//		return "redirect:/h2-console";
	//
	//	}

	@GetMapping("/")
	public String indexGetHandler(
		Model model,
		@PageableDefault(page = 0, size = 5, sort = "id") Pageable pageable
	) {

		Page<Book> pageBook = bookRepository.findAll(pageable);
		List<Book> listBook = pageBook.getContent();

		// コンソールで確認する。
		System.out.println("■取得レコードを出力する。");
		listBook.forEach(
				bl -> System.out.println(
						"Id ［" + bl.getId() + "］" + "Title［" + bl.getTitle() + "］"));

		// pageBook は、Pagination のリンクを生成する為に必要なので、Model に追加する。
		model.addAttribute("pageBook", pageBook);
		model.addAttribute("listBook", listBook);
		return "pagnationList";

	}

	@GetMapping("/pagnationList")
	public String pagnationListGetHandler(
		Model model,
		@PageableDefault(page = 0, size = 5, sort = "id") Pageable pageable
	) {

		return indexGetHandler(model, pageable);

	}

}
