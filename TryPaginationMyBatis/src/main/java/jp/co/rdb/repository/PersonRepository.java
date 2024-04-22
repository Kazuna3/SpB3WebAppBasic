package jp.co.rdb.repository;

import java.util.List;

import jp.co.rdb.entity.Person;

// Mapper Interface
// @MapperScan を書かない場合は、Mapper Repository のクラスに @Mapper アノテーションを付ける必要がある。
// @Mapper
public interface PersonRepository {

	Long count();

	public List<Person> selectAll();

}
