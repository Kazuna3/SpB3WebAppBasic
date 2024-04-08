package jp.co.rdb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.co.rdb.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

}
