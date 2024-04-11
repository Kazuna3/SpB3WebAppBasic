package jp.co.rdb.service;

import java.util.Optional;

import jp.co.rdb.entity.Book;

public interface BookService {

	// Book テーブルのレコードを登録する
	public void saveBook(Book book);

	// クイズの全レコードを抽出する
	// リポジトリで JpaRepository を拡張したら使用可となるメソッドである。
	// public List<Book> findAllBook();

	// クイズの全レコードを抽出する
	// リポジトリで CrudRepository を祖先に持つ JpaRepository を拡張したら使用可となるメソッドである。
	// リポジトリで CrudRepository を拡張したら使用可となるメソッドである。
	public Iterable<Book> findAllBook();

	// クイズのレコード件数を返す
	public long countBook();

	// クイズのレコードを削除する
	public void deleteByIdBook(Integer id);

	// id を検索条件として、クイズのレコードを検索し、その該当レコードを返す。
	public Optional<Book> findByIdBook(Integer id);

}
