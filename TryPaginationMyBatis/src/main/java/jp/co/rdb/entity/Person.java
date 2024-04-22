package jp.co.rdb.entity;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Person implements Serializable {

	/*
	CREATE TABLE IF NOT EXISTS person
	(
		id serial PRIMARY KEY
	,	name VARCHAR (100)
	);
	 */

	private Integer id;

	private String name;

}
