package jp.co.rdb.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import jp.co.rdb.entity.Book;

// @formatter:off
public interface BookRepository
	extends
		// ①②の何れか1つ、或いは両方をコメントインすると正常に動作する。
		// ②は①を祖先に持つ持っている為、上述の動作となる模様。
		// ①だけでも動作は変わらない。また、①だけにした方がコンパクトになるように思うので、
		// ②はコメントアウトすることにした。
		CrudRepository<Book, Integer>,	// ①
		// JpaRepository<Book, Integer>,	// ②
		PagingAndSortingRepository<Book, Integer> {
// @formatter:on

}
