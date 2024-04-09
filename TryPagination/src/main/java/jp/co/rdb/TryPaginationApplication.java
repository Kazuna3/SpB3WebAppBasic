package jp.co.rdb;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.thymeleaf.expression.Numbers;

import jp.co.rdb.entity.Book;
import jp.co.rdb.service.BookService;
import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class TryPaginationApplication {

	private final BookService bookService;

	public static void main(String[] args) {

		SpringApplication.run(TryPaginationApplication.class, args);

		// import org.springframework.context.ApplicationContext;
		// ApplicationContext context = SpringApplication.run(TryPaginationApplication.class, args);
		// TryPaginationApplication app = context.getBean(TryPaginationApplication.class);
		// app.execute();

	}

	@SuppressWarnings("unused")
	private void execute() {

		insertBook();
		findAllBook();
		countBook();
		deleteBook();
		findByIdBook();
		System.out.println("(^0^)/(^0^)/(^0^)/(^0^)/(^0^)/(^0^)/(^0^)/(^0^)/");
		findAllBook();
		countBook();

		dousaKakunin();

	}

	// @SuppressWarnings("unused")
	private void insertBook() {

		Book book = new Book(null, "今年は潜りに行こうと思っている！");
		bookService.saveBook(book);

	}

	// @SuppressWarnings("unused")
	private void findAllBook() {

		Iterable<Book> books = bookService.findAllBook();

		// @formatter:off
		books.forEach(
				b -> System.out.println(
					"ID［" + b.getId() + "］, " + "Title［" + b.getTitle() + "］"
				)
		);
		// @formatter:on

	}

	// @SuppressWarnings("unused")
	private void countBook() {

		System.out.println("Book テーブルのレコード件数［" + bookService.countBook() + "］");

	}

	// @SuppressWarnings("unused")
	private void deleteBook() {

		bookService.deleteByIdBook(1);

	}

	// @SuppressWarnings("unused")
	private void findByIdBook() {

		Optional<Book> book = bookService.findByIdBook(3);

		if (null == book) {

			System.out.println("指定された ID のレコードは、無かったです。");

		} else {

			System.out.println("指定された ID のレコードは、次の通りです。");
			// @formatter:off
			System.out.println(
				"ID［" + book.get().getId() + "］, " + "Title［" + book.get().getTitle() + "］"
			);
			// @formatter:on
		}

	}

	// @SuppressWarnings("unused")
	private void dousaKakunin() {

		System.out.println("■Numbersのsequenceメソッドの動作を確認する。");

		Numbers number = new Numbers(Locale.JAPANESE);
		Integer[] aryInteger = number.sequence(0, -10);

		List<Integer> list = Arrays.asList(aryInteger);

		list.forEach(i -> System.out.println(i));

	}

}
