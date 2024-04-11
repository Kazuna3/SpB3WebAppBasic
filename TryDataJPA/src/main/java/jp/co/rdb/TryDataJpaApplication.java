package jp.co.rdb;

import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import jp.co.rdb.entity.Book;
import jp.co.rdb.service.BookService;
import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class TryDataJpaApplication {

	private final BookService bookService;

	public static void main(String[] args) {

		// SpringApplication.run(TryDataJpaApplication.class, args);

		ApplicationContext context = SpringApplication.run(TryDataJpaApplication.class, args);
		TryDataJpaApplication app = context.getBean(TryDataJpaApplication.class);

		app.execute();

	}

	// @SuppressWarnings("unused")
	private void execute() {

		insertBook();
		findAllBook();
		countBook();
		deleteBook();
		findByIdBook();
		System.out.println("(^0^)/(^0^)/(^0^)/(^0^)/(^0^)/(^0^)/(^0^)/(^0^)/");
		findAllBook();
		countBook();

	}

	// @SuppressWarnings("unused")
	private void insertBook() {

		Book book = new Book(null, "今年は潜りに行こうと思っている！");
		bookService.saveBook(book);

	}

	// @SuppressWarnings("unused")
	private void findAllBook() {

		// リポジトリで JpaRepository を拡張したら使用可となるメソッドである。
		// List<Book> books = bookService.findAllBook();

		// リポジトリで CrudRepository を祖先に持つ JpaRepository を拡張したら使用可となるメソッドである。
		// リポジトリで CrudRepository を拡張したら使用可となるメソッドである。
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

}
