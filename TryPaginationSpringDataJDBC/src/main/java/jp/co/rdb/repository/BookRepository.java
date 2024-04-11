package jp.co.rdb.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import jp.co.rdb.entity.Book;

public interface BookRepository
		extends
		CrudRepository<Book, Integer>,
		PagingAndSortingRepository<Book, Integer> {

}
