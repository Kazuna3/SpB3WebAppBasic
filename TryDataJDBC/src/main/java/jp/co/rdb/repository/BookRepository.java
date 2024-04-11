package jp.co.rdb.repository;

import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import jp.co.rdb.entity.Book;

public interface BookRepository extends CrudRepository<Book, Integer> {

	@Query("SELECT id, title FROM book ORDER BY RANDOM() limit 1")
	Optional<Book> findRandomBook();

}
