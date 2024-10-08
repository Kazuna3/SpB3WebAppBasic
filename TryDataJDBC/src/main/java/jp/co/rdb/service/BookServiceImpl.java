package jp.co.rdb.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import jp.co.rdb.entity.Book;
import jp.co.rdb.repository.BookRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

	private final BookRepository bookRepository;

	// Book テーブルに、レコードを登録する。
	@Override
	public void saveBook(Book book) {

		bookRepository.save(book);

	}

	// Book テーブルの全レコードを抽出する。
	@Override
	public Iterable<Book> findAllBook() {

		Iterable<Book> iterableBook = bookRepository.findAll();
		return iterableBook;

	}

	// Book テーブルのレコード件数を返す。
	@Override
	public long countBook() {

		return bookRepository.count();

	}

	// Book テーブルのレコードを削除する。
	@Override
	public void deleteByIdBook(Integer id) {

		bookRepository.deleteById(id);

	}

	// id を検索条件として、Book テーブルのレコードを検索し、その該当レコードを返す。
	@Override
	public Optional<Book> findByIdBook(Integer id) {

		Optional<Book> book = bookRepository.findById(id);
		return book;

	}

	// ランダムにレコードを１件抽出して返す。
	@Override
	public Optional<Book> findRandomBook() {

		Optional<Book> book = bookRepository.findRandomBook();
		return book;

	}

}
