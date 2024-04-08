package jp.co.rdb.repository;

import org.springframework.data.repository.CrudRepository;

import jp.co.rdb.entity.Book;

public interface BookRepository extends CrudRepository<Book, Integer> {

	//	@Query("SELECT id FROM quiz ORDER BY RANDOM() limit 1")
	//	Integer getRandomId(); // 左記は抽象メソッドである。

	//	@Query("SELECT\n"
	//			+ "    id\n"
	//			+ "    , question\n"
	//			+ "    , answer\n"
	//			+ "    , author \n"
	//			+ "FROM\n"
	//			+ "    quiz \n"
	//			+ "ORDER BY\n"
	//			+ "    RANDOM() \n"
	//			+ "limit\n"
	//			+ "    1;")
	//	//	@Query("SELECT id,question, answer, author FROM quiz ORDER BY RANDOM() limit 1;")
	//	Optional<Quiz> getRandomQuiz(); // 左記は抽象メソッドである。

}
