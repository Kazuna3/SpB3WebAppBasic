package jp.co.rdb.entity;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Book {

	@Id
	// ID IDENTITY NOT NULL PRIMARY KEY,
	private Integer id;

	// TITLE VARCHAR(255) NOT NULL
	private String title;

}
